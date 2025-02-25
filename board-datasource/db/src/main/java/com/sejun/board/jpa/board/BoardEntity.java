package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.Board;
import com.sejun.board.domain.board.BoardContent;
import com.sejun.board.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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
