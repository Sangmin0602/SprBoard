package spr.board.dao;

import java.util.List;

import spr.board.model.PostVO;

public interface IPostDao {

	public List<PostVO> findAll();
}
