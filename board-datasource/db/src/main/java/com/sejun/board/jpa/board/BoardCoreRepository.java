package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BoardCoreRepository implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;

    public BoardCoreRepository(BoardJpaRepository boardJpaRepository, BoardQueryRepository boardQueryRepository) {
        this.boardJpaRepository = boardJpaRepository;
        this.boardQueryRepository = boardQueryRepository;
    }

    @Override
    public List<Board> find(Cursor cursor) {
        List<BoardEntity> boardEntities = cursor.getSortType() == SortType.ASC
                ? boardQueryRepository.findASC(cursor.getOffset(), cursor.getLimit(), cursor.getSortField())
                : boardQueryRepository.findDESC(cursor.getOffset(), cursor.getLimit(), cursor.getSortField());
        return boardEntities.stream()
                .map(BoardEntity::toBoard)
                .collect(Collectors.toList());
    }

    @Override
    public Board find(Long boardId) {
        return findBoardOrThrow(boardId).toBoard();
    }

    @Override
    public Long save(Board board) {
        return boardJpaRepository.save(BoardEntity.create(board.getTitle(), board.getContent(), board.getUserId())).getId();
    }

    @Override
    @Transactional
    public Board modify(Long boardId, BoardContent boardContent) {
        BoardEntity findBoardEntity = findBoardOrThrow(boardId);
        findBoardEntity.update(boardContent);
        return findBoardEntity.toBoard();
    }

    @Override
    public Long removeBoard(Board board) {
        BoardEntity findBoardEntity = findBoardOrThrow(board.getId());

        findBoardEntity.delete();
        return findBoardEntity.getId();
    }

    private BoardEntity findBoardOrThrow(Long boardId) {
        return boardJpaRepository.findById(boardId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("not found board with id" + boardId);
                });
    }
}