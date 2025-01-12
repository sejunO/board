package com.sejun.board.domain.board;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardReader {

    private final BoardRepository boardRepository;

    public BoardReader(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> find(Long offset, int limit) {
        return boardRepository.find(offset, limit);
    }
}
