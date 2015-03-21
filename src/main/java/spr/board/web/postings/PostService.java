package spr.board.web.postings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.BoardDataBean;
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

	/**
	 * 목록 조회
	 * @return
	 * @throws SQLException
	 */
	public int getArticleCount() throws SQLException {
		int cnt = repo.getPostDao().getTotalCount();
		return cnt;
	}

	public List<BoardDataBean> getArticles(int startRow, int pageSize) {
		List<BoardDataBean> lists = new ArrayList<BoardDataBean>();
		lists = repo.getPostDao().getArticles(startRow, pageSize);
		
		return lists;
	}

	public void insertArticle(BoardDataBean article) {
		repo.getPostDao().getInsertArticle(article);
	}

	public BoardDataBean getArticle(int num) throws SQLException {
		BoardDataBean board = repo.getPostDao().getArticle(num);
		return board;
	}
	
	public BoardDataBean updateGetArticle(int num) throws SQLException {
		BoardDataBean board = repo.getPostDao().updateGetArticle(num);
		return board;
	}

	public void updateArticle(BoardDataBean article) throws SQLException {
		repo.getPostDao().updateArticle(article);
	}

	public int deleteArticle(int num, String passwd) throws SQLException {
		int check = repo.getPostDao().deleteArticle(num, passwd);
		return 0;
	}
}
