package com.sejun.board.jpa.board;

import com.sejun.board.domain.board.Board;
import com.sejun.board.domain.board.BoardRepository;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCoreRepository implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository BoardQueryRepository;

    @Override
    public List<Board> find(Long cursor, int limit) {
        List<BoardEntity> boardEntities = BoardQueryRepository.find(cursor, limit);
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
}
