package com.upgrad.quora.service.business;


import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonBusinessService {

	@Autowired
	private UserDao userDao;

	//@Autowired
	//private QuestionDao questionDao;        //press Alt+Enter once Rinsheed implements the Dao

	//@Autowired
	//private AnswerDao answerDao;            //press Alt+Enter once Saransh implements the Dao

	// Method to get userdetails based on Uuid
	public UserEntity getUser(String Uuid) throws UserNotFoundException {
		UserEntity userDetails = userDao.getUser(Uuid);
		if (userDetails == null) {
			throw new UserNotFoundException("USR-001", "User with entered uuid does not exist");
		}
		return userDetails;
	}

	// Method to get user based on AccessToken
	@Transactional(propagation = Propagation.REQUIRED)
	public UserAuthEntity validateUser(String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthEntity = userDao.validateUser(token);
		if (userAuthEntity == null) {
			throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
		}
		return userAuthEntity;
	}

	// Method to get details of the signed in user
	public UserAuthEntity getCurrentUserDeatils(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to get user details");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question
	public UserAuthEntity getCurrentUserQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to post a question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in answer
	public UserAuthEntity getCurrentUserQuestionAnswer(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to post an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getAllUserQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to get all questions");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getEditUserQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to edit the question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getEditUserAnswerDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to edit an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getDeleteUserQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to delete a question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getDeleteAnswerDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to delete an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in question for all user
	public UserAuthEntity getAllUserByIdQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002",
					"User is signed out.Sign in first to get all questions posted by a specific user");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in answer for all user
	public UserAuthEntity getAllAnswerDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002",
					"User is signed out.Sign in first to get the answers");
		}
		return userAuthDetails;
	}

	//will implement code with question and answer Dao once they complete
}
