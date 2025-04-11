package org.sopt.repository;

import java.util.Optional;
import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }

        return false;
    }

    public boolean updateTitleById(int id, String title) {
        for (Post post : postList) {
            if (post.getId() == id) {
                post.updateTitle(title);
                return true;
            }
        }

        return false;
    }

    public List<Post> findPostsByKeyword(String keyword) {
        List<Post> results = new ArrayList<>();
        for (Post post : postList) {
            if (post.getTitle().contains(keyword)) {
                results.add(post);
            }
        }

        return results;
    }

    public boolean existsByTitle(String title) {
        return postList.stream()
                .anyMatch(post -> post.getTitle().equals(title));
    }

    public Optional<Post> findLastPost() {
        if (!postList.isEmpty()) {
            return Optional.ofNullable(postList.get(postList.size() - 1));
        }

        return Optional.empty();
    }
}
