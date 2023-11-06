package com.chunjae.pro05.persis;

import com.chunjae.pro05.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BoardMapper {
    List<Board> boardList();

    Board boardDetail(int bno);

    void boardInsert(Board board);
}
