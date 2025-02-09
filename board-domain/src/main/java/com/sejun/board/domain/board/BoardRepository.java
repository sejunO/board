package com.sejun.board.domain.board;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {
    List<Board> find(Cursor cursor);

    Board find(Long boardId);

    Long save(Board board);

    Board modify(Long boardId, BoardContent boardContent);

    Long removeBoard(Board board);
}
