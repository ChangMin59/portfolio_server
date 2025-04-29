package com.example.basic.dto;

import lombok.*;
import com.example.basic.entity.BoardEntity;

@Getter
@Setter
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String writerName;

    public BoardDTO(Long id, String title, String content, String writerName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }

    public static BoardDTO fromEntity(BoardEntity entity) {
        return new BoardDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getWriter() != null ? entity.getWriter().getName() : "(알 수 없음)"
        );
    }
}