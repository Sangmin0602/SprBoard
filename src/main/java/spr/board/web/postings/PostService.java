package spr.board.web.postings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.PostVO;
import spr.board.model.UserVO;

@Service
public class PostService {
	@Autowired
	private IDaoRepository repo;
	
	public List<PostVO> findAllPosts() {
		List<PostVO> allPosts = repo.getPostDao().findAll();
		return allPosts;
	}

	public PostVO findBySeq(String postSeq) {
		int seq = Integer.parseInt(postSeq);
		PostVO post = repo.getPostDao().findBySeq(seq, true);
		return post;
	}

	public PostVO insertPost(String title, String content, Integer writerSeq) {
		UserVO writer = repo.getUserDao().findBySeq(writerSeq);
		PostVO post = new PostVO(title, content, writer);
		repo.getPostDao().insert(post);
		return post;
	}
}
