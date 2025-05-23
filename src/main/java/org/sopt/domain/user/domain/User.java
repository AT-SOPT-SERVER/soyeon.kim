package org.sopt.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.global.error.BusinessException;
import org.sopt.global.util.GraphemeClusterUtil;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private final List<Post> posts = new ArrayList<>();

    protected User() {

    }

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        isNameBlank(name);
        isNameLessThan10(name);
    }

    private void isNameBlank(String name) {
        if (name.isBlank()) {
            throw new BusinessException(UserErrorCode.INVALID_NAME_BLANK);
        }
    }

    private void isNameLessThan10(String name) {
        if (GraphemeClusterUtil.countGraphemeClusters(name) > 10) {
            throw new BusinessException(UserErrorCode.INVALID_NAME_LENGTH);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
