package com.sejun.board.domain.board;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardReader boardReader;
    private final BoardProcessor boardProcessor;

    public BoardService(BoardReader boardReader, BoardProcessor boardProcessor) {
        this.boardReader = boardReader;
        this.boardProcessor = boardProcessor;
    }

    public List<Board> find(Cursor cursor) {
        return boardReader.find(cursor);
    }

    public Long createBoard(Board board) {
        return boardProcessor.save(board);
    }

    public Long removeBoard(Board board) {
        return boardProcessor.removeBoard(board);
    }
}
