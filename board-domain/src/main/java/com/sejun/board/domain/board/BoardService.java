package com.sejun.board.domain.board;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> find(Long offset, int limit) {
        return boardRepository.find(offset, limit);
    }

    public Long save(Board board) {
        return boardRepository.save(board);
    }
}
