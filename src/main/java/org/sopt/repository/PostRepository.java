package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final List<Post> postList = new ArrayList<>();

    public void save(final Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(final int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    public boolean delete(final int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public boolean updateTitleById(final int id, final String title) {
        for (Post post : postList) {
            if (post.getId() == id) {
                post.updateTitle(title);
                return true;
            }
        }
        return false;
    }
}