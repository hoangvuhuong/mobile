package adstech.vn.com.mobile.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.repository.CommentRepository;
import adstech.vn.com.mobile.repository.PostRepository;
import adstech.vn.com.mobile.util.CommonConstant;

@Service
public class ActionService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	public ResponseContract<?> createPost(Post post) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Đăng bài thành công",
					postRepository.create(post));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> getAllPost() {
		try {
			return new ResponseContract<List<Post>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null,
					postRepository.getAll());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> deletePostByUser(Integer postId, Integer userId) {
		try {
			Post post = postRepository.findById(postId);
			if (post != null) {
				if (post.getUserId() == userId) {
					return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Xóa thành công",
							postRepository.delete(postId));
				} else {
					return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE,
							"Không có quyền xóa bài viết", null);
				}
			}
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Không tìm thấy bài viết",
					null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> getPostByUserID(Integer userID) {
		try {
			return new ResponseContract<List<Post>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null,
					postRepository.getByUserId(userID));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> createComment(Comment comment) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Thanh cong",
					commentRepository.create(comment));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> updateComment(Comment comment) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Thanh Cong",
					commentRepository.update(comment));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> getCommentByPostId(Integer postId) {
		try {
			return new ResponseContract<List<Comment>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null,
					commentRepository.getByPostId(postId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> deleteComment(Integer commentId){
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "", commentRepository.delete(commentId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
			
		}
	}
}
