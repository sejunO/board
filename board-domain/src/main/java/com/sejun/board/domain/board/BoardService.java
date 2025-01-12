package com.sejun.board.domain.board;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardReader boardReader;
    private final BoardProcessor boardProcessor;

    public BoardService(BoardReader boardReader, BoardProcessor boardProcessor) {
        this.boardReader = boardReader;
        this.boardProcessor = boardProcessor;
    }

    public List<Board> find(Long offset, int limit) {
        return boardReader.find(offset, limit);
    }

    public Long create(Board board) {
        return boardProcessor.save(board);
    }
}
