package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;
import org.sopt.util.FileIOUtil;
import org.sopt.util.IdGenerator;
import org.sopt.validator.PostValidator;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostValidator postValidator;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postValidator = new PostValidator(postRepository);
    }

    public void createPost(String title) {
        postValidator.validateAll(title);
        Post post = new Post(IdGenerator.autoIncrement(), title);
        postRepository.save(post);
        System.out.println(post.getTitle());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public boolean updatePostTitle(int id, String title) {
        postValidator.validateAll(title);
        return postRepository.updateTitleById(id, title);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findPostsByKeyword(keyword);
    }

    public boolean createFile() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new IllegalStateException("⚠️ 현재 작성된 게시글이 없습니다. 게시글을 먼저 작성해 주세요!");
        }
        FileIOUtil.saveToFile(posts, post -> String.format("🆔 %d | 📌 제목: %s", post.getId(), post.getTitle()));
        return true;
    }
}
