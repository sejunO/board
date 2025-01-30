package com.sejun.board.domain.board;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository {
    List<Board> find(Long offset, int limit);

    Long save(Board board);

    Long removeBoard(Board board);
}
