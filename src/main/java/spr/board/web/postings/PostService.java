package spr.board.web.postings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.PostVO;

@Service
public class PostService {
	@Autowired
	private IDaoRepository repo;
	
	public List<PostVO> findAllPosts() {
		List<PostVO> allPosts = repo.getPostDao().findAll();
		return allPosts;
	}
}
