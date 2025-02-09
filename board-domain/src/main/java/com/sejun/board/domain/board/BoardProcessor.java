package com.sejun.board.domain.board;

import org.springframework.stereotype.Component;

@Component
public class BoardProcessor {

    private final BoardRepository boardRepository;

    public BoardProcessor(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long save(Board board) {
        return boardRepository.save(board);
    }

    public Board modify(Long boardId, BoardContent boardContent) {
        return boardRepository.modify(boardId, boardContent);
    }

    public Long removeBoard(Board board) {
        return boardRepository.removeBoard(board);
    }
}
