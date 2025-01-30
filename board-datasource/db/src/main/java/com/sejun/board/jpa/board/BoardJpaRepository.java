package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT new com.sejun.board.domain.board.Board(b.id, b.title, b.content) " +
        "FROM BoardEntity b " +
        "WHERE b.id > :lastId " +
        "ORDER BY b.id ASC")
    List<Board> findASC(@Param("lastId") Long lastId, @Param("limit") int limit);
}
