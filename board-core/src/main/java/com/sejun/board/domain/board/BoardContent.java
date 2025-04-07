package com.sejun.board.domain.board;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class BoardContent {
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
