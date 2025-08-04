package org.sopt.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.util.GraphemeClusterUtil;
import org.sopt.user.domain.exception.UserErrorCode;
import org.sopt.user.domain.exception.UserException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
            throw new UserException(UserErrorCode.INVALID_NAME_BLANK);
        }
    }

    private void isNameLessThan10(String name) {
        if (GraphemeClusterUtil.countGraphemeClusters(name) > 10) {
            throw new UserException(UserErrorCode.INVALID_NAME_LENGTH);
        }
    }
}
