package com.chunjae.pro05.biz;

import com.chunjae.pro05.entity.Board;
import com.chunjae.pro05.persis.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public List<Board> getBoardList() {
        return boardMapper.boardList();
    }

    public Board getBoardDetail(int bno) {
        return boardMapper.boardDetail(bno);
    }

    public void insertBoard(Board board) {
        boardMapper.boardInsert(board);
    }
}
