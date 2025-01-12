package com.sejun.board.jpa.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<BoardEntity> find(Long lastId, int limit) {
        QBoardEntity board = QBoardEntity.boardEntity;

        return queryFactory.selectFrom(board)
                .where(board.id.gt(lastId))
                .orderBy(board.id.asc())
                .limit(limit)
                .fetch();
    }
}
