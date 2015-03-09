package spr.board.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import spr.board.model.PostVO;

@Repository
public interface IPostDao {

	public List<PostVO> findAll();
}
