package com.sejun.board.controller.v1.response;

import com.sejun.board.domain.board.Board;

public class DefaultIdResponse {
    private Long id;
    
    public DefaultIdResponse(Long id) {
        this.id = id;
    }
    
    public static DefaultIdResponse of(Board board) {
        return new DefaultIdResponse(board.getId());
    }
    
    public Long getId() {
        return id;
    }
}
