package com.sejun.board.jpa.board;

import com.sejun.board.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    private String title;
    private String content;

    @Builder
    public BoardEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateContent(String newContent) {
        if (newContent == null || newContent.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        this.content = newContent;
    }
}
