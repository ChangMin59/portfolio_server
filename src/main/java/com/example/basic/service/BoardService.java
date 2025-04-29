package com.example.basic.service;

import com.example.basic.dto.BoardDTO;
import com.example.basic.entity.BoardEntity;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.BoardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepo boardRepo;

    // 회원 정보까지 포함한 게시글 저장 메서드
    public BoardEntity createBoard(BoardDTO boardDTO, JoinEntity loginUser) {
        BoardEntity board = new BoardEntity();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(loginUser); // 작성자 설정
        return boardRepo.save(board);
    }

    // 모든 게시물 가져오는 메서드
    public List<BoardDTO> getAllBoards() {
        List<BoardEntity> boardEntities = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return boardEntities.stream()
                .map(BoardDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public BoardDTO getBoardById(Long id) {
        BoardEntity board = boardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDTO.fromEntity(board);
    }

}