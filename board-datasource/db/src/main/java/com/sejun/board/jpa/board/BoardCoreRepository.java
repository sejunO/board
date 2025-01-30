package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.Board;
import com.sejun.board.domain.board.BoardRepository;
import com.sejun.board.domain.board.Cursor;
import com.sejun.board.domain.board.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardCoreRepository implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository BoardQueryRepository;

    @Override
    public List<Board> find(Cursor cursor) {
        List<BoardEntity> boardEntities = cursor.getSortType() == SortType.ASC
                ? BoardQueryRepository.findASC(cursor.getOffset(), cursor.getLimit(), cursor.getSortField())
                : BoardQueryRepository.findDESC(cursor.getOffset(), cursor.getLimit(), cursor.getSortField());
        return boardEntities.stream()
                .map(BoardEntity::toBoard)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Board board) {
        return boardJpaRepository.save(BoardEntity.builder()
            .title(board.getTitle())
            .content(board.getContent())
            .build()).getId();
    }

    @Override
    public Long removeBoard(Board board) {
        BoardEntity findBoardEntity = boardJpaRepository.findById(board.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        findBoardEntity.delete();
        return findBoardEntity.getId();
    }
}
