package org.sopt.post.application.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.global.error.BusinessException;
import org.sopt.post.application.dto.response.GetAllPostsServiceResponse;
import org.sopt.post.application.dto.response.GetDetailedPostServiceResponse;
import org.sopt.post.application.dto.response.GetSimplePostServiceResponse;
import org.sopt.post.application.dto.response.SearchPostServiceResponse;
import org.sopt.post.application.dto.response.SearchResultServiceResponse;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.Tag;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.post.presentation.exception.PostErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostQueryService {

    private final PostRepository postRepository;

    public GetAllPostsServiceResponse getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<GetSimplePostServiceResponse> result = posts.stream()
                                                        .map(GetSimplePostServiceResponse::from)
                                                        .toList();

        return new GetAllPostsServiceResponse(result);
    }

    public SearchResultServiceResponse searchPostsByKeyword(String keyword, String type) {
        List<Post> posts = getPosts(keyword, type);
        List<SearchPostServiceResponse> result = posts.stream()
                                                     .map(SearchPostServiceResponse::from)
                                                     .toList();

        return new SearchResultServiceResponse(result);
    }

    public GetDetailedPostServiceResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        return GetDetailedPostServiceResponse.from(post);
    }

    private List<Post> getPosts(String keyword, String type) {
        return switch (type) {
            case "title" -> postRepository.findPostsByTitleContaining(keyword);
            case "user" -> postRepository.findPostsByUser_nameContaining(keyword);
            case "tag" -> postRepository.findPostsByTag(Tag.fromKoreanName(keyword));
            default -> throw new BusinessException(PostErrorCode.INVALID_SEARCH_TYPE);
        };
    }
}
