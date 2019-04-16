package com.dy.project.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dy.project.board.dto.BoardDTO;

/* MyBatis의 Mapper 인터페이스임을 명시 */

/*
 * MyBatis의 Mapper 인터페이스임을 명시
 * MyBatis는 DAO 클래스를 만드는 것보다 SqlSessionDaoSupports 또는 SqlSessionTemplate을 사용하기를 권장
 */
@Mapper
public interface BoardMapper {

	public List<BoardDTO> selectBoardList();

}
