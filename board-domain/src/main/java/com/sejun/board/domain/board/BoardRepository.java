package com.sejun.board.domain.board;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {
    List<Board> find(Cursor cursor);

    Long save(Board board);

    Long removeBoard(Board board);
}
