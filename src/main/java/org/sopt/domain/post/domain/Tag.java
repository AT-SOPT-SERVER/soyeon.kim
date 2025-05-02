package org.sopt.domain.post.domain;

import java.util.Arrays;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.global.error.BusinessException;

public enum Tag {
    BACKEND("백엔드"),
    DATABASE("데이터베이스"),
    INFRA("인프라");

    private final String name;

    Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Tag fromKoreanName(String name) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BusinessException(PostErrorCode.INVALID_TAG_TYPE));
    }
}
