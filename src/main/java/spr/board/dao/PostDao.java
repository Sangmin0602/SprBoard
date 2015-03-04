package spr.board.dao;

import java.util.ArrayList;
import java.util.List;

import spr.board.model.PostVO;

public class PostDao implements IPostDao {

	@Override
	public List<PostVO> findAll() {
		ArrayList<PostVO> posts = new ArrayList<PostVO>();
		posts.add( new PostVO(3000, "first", "first contents"));
		posts.add( new PostVO(3001, "ssssirst", "first contents"));
		posts.add( new PostVO(3002, "fdfasdfasd st", "first contents"));
		return posts;
	}

}
