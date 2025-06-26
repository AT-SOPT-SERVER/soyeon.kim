package org.sopt.post.application.query;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.*;

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
import org.sopt.post.application.exception.PostErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostQueryService {

    private final PostRepository postRepository;

    public GetAllPostsServiceResponse getAllPosts(int page, int size) {
        Page<Post> postPage = postRepository.findAllByDeletedFalse(
            of(page, size, by(Direction.DESC, "createdAt"))
        );

        List<GetSimplePostServiceResponse> result = postPage.getContent().stream()
                                                        .map(GetSimplePostServiceResponse::from)
                                                        .toList();

        return new GetAllPostsServiceResponse(result, page, size, postPage.getTotalPages(),
            postPage.getTotalElements());
    }

    public SearchResultServiceResponse searchPostsByKeyword(String keyword, String type) {
        List<Post> posts = getPosts(keyword, type);
        List<SearchPostServiceResponse> result = posts.stream()
                                                     .map(SearchPostServiceResponse::from)
                                                     .toList();

        return new SearchResultServiceResponse(result);
    }

    public GetDetailedPostServiceResponse getPostById(Long id) {
        Post post = getPost(id);

        return GetDetailedPostServiceResponse.from(post);
    }

    private Post getPost(Long id) {
        return postRepository.findByIdAndDeletedFalse(id)
                   .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
    }

    private List<Post> getPosts(String keyword, String type) {
        return switch (type) {
            case "title" -> postRepository.findPostsDeletedFalseAndByTitleContaining(keyword);
            case "user" -> postRepository.findPostsByDeletedFalseAndUser_nameContaining(keyword);
            case "tag" -> postRepository.findPostsDeletedFalseAndByTag(Tag.fromKoreanName(keyword));
            default -> throw new BusinessException(PostErrorCode.INVALID_SEARCH_TYPE);
        };
    }
}
