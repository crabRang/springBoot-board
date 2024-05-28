package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql; //mapper호출을 위한 필드
    public void save(BoardDTO boardDTO) {
        //Board : namespace, save : 쿼리문을 담고있는 쿼리, boardDTO : DB로 넘길객체
        //한번에 한개의 객체만 넘길 수 있다
        //두개 이상의 파라미터를 넘겨야할 경우 hashMap같은걸 이용해야함
        sql.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }
}
