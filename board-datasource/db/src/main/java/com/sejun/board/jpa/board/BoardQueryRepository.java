package com.sejun.board.jpa.board;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejun.board.domain.board.SortField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BoardQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<BoardEntity> findASC(Long offset, int limit, SortField sortField) {
        QBoardEntity board = QBoardEntity.boardEntity;
        return queryFactory.selectFrom(board)
                .offset(offset)
                .orderBy(getAscOrder(sortField, board))
                .limit(limit)
                .fetch();
    }

    public List<BoardEntity> findDESC(Long offset, int limit, SortField sortField) {
        QBoardEntity board = QBoardEntity.boardEntity;

        return queryFactory.selectFrom(board)
                .offset(offset)
                .orderBy(getDescOrder(sortField, board))
                .limit(limit)
                .fetch();
    }

    private OrderSpecifier<?> getAscOrder(SortField sortField, QBoardEntity board) {
        if (sortField == SortField.CREATED_AT) return board.createdAt.asc();
        if (sortField == SortField.TITLE) return board.title.asc();
        return board.id.asc();
    }

    private OrderSpecifier<?> getDescOrder(SortField sortField, QBoardEntity board) {
        if (sortField == SortField.CREATED_AT) return board.createdAt.desc();
        if (sortField == SortField.TITLE) return board.title.desc();
        return board.id.desc();
    }
}
