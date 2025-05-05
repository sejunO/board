package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.Board;
import com.sejun.board.domain.board.BoardContent;
import com.sejun.board.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class BoardEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    public static BoardEntity create(String title, String content, Long userId) {
        validate(title, content, userId);
        return new BoardEntity(title, content, userId);
    }

    @Builder
    private BoardEntity(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    private static void validate(String title, String content, Long userId) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("내용은 비어 있을 수 없습니다.");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId는 필수입니다.");
        }
    }
    public void update(BoardContent boardContent) {
        updateTitle(boardContent.getTitle());
        updateContent(boardContent.getContent());
    };
    private void updateContent(String newContent) {
        if (!StringUtils.hasText(newContent)) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        this.content = newContent;
    }

    private void updateTitle(String newTitle) {
        if (!StringUtils.hasText(newTitle)) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        this.title = newTitle;
    }

    public Board toBoard() {
        return Board.builder()
                .id(getId())
                .title(getTitle())
                .content(getContent())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
