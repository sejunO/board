package com.sejun.board.controller.v1.request;

import com.sejun.board.domain.board.BoardContent;

@AtLeastOneNotBlank
public record ModifyBoardRequest(
        String title,
        String content) {
    public BoardContent toContent() {
        return BoardContent.builder()
                .title(title)
                .content(content)
                .build();
    }
}
