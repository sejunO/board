package com.sejun.board.controller.v1.request;

import com.sejun.board.domain.board.BoardContent;

public class ModifyBoardRequest {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public BoardContent toContent() {
        return BoardContent.builder()
                .title(title)
                .content(content)
                .build();
    }
}
