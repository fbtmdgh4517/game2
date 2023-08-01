package com.game.mapper;

import java.util.List;

import com.game.vo.BoardInfoVO2;

public interface BoardInfoMapper2 {

	List<BoardInfoVO2> selectBoardInfoList(BoardInfoVO2 board);
	BoardInfoVO2 selectBoardInfo(BoardInfoVO2 board);
	int insertBoardInfo(BoardInfoVO2 board);
	int updateBoardInfo(BoardInfoVO2 board);
	int deleteBoardInfo(BoardInfoVO2 board);
}
