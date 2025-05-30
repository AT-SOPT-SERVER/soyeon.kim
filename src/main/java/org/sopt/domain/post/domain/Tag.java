package org.sopt.domain.post.domain;

import java.util.Arrays;
import lombok.Getter;
import org.sopt.domain.post.presentation.exception.PostErrorCode;
import org.sopt.global.error.BusinessException;

@Getter
public enum Tag {
    BACKEND("백엔드"),
    DATABASE("데이터베이스"),
    INFRA("인프라");

    private final String name;

    Tag(String name) {
        this.name = name;
    }

    public static Tag fromKoreanName(String name) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BusinessException(PostErrorCode.INVALID_TAG_TYPE));
    }
}
