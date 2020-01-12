package com.upgrad.quora.service.business;


import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.dao.QuestionDao;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonBusinessService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;


	// Method to get userdetails based on Uuid  #1
	public UserEntity getUser(String Uuid) throws UserNotFoundException {
		UserEntity userDetails = userDao.getUser(Uuid);
		if (userDetails == null) {
			throw new UserNotFoundException("USR-001", "User with entered uuid does not exist");
		}
		return userDetails;
	}

	// Method to get user based on AccessToken  #2
	@Transactional(propagation = Propagation.REQUIRED)
	public UserAuthEntity validateUser(String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthEntity = userDao.validateUser(token);
		if (userAuthEntity == null) {
			throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
		}
		return userAuthEntity;
	}

	// Method to get details of the signed in user  #3
	public UserAuthEntity getCurrentUserDeatils(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to get user details");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to post question  #4
	public UserAuthEntity getCurrentUserQuestionDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to post a question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to post answer  #5
	public UserAuthEntity getCurrentUserQuestionAnswer(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to post an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to retrieve all question  #6
	public UserAuthEntity getAllUserQuestionDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to get all questions");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to edit question  #7
	public UserAuthEntity getEditUserQuestionDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to edit the question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to edit answer  #8
	public UserAuthEntity getEditUserAnswerDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to edit an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in in user to delete question  #9
	public UserAuthEntity getDeleteUserQuestionDetails(String userId, String token)
			throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to delete a question");
		}
		return userAuthDetails;
	}

	// Method to get details of the signed in user to delete answer  #10
	public UserAuthEntity getDeleteAnswerDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to delete an answer");
		}
		return userAuthDetails;
	}

	// Method to get details of all questions  #11
	public UserAuthEntity getAllUserByIdQuestionDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002",
					"User is signed out.Sign in first to get all questions posted by a specific user");
		}
		return userAuthDetails;
	}

	// Method to get details of all answers  #12
	public UserAuthEntity getAllAnswerDetails(String userId, String token) throws AuthorizationFailedException {
		UserAuthEntity userAuthDetails = userDao.getUserAuthDetails(userId, token);
		if (userAuthDetails == null) {
			throw new AuthorizationFailedException("ATHR-002",
					"User is signed out.Sign in first to get the answers");
		}
		return userAuthDetails;
	}

	// Method to get question Details based on questionID  #13
	public QuestionEntity findQuestionById(String questionId) throws InvalidQuestionException {
		QuestionEntity question = questionDao.findQuestionById(questionId);
		if (question == null) {
			throw new InvalidQuestionException("QUES-001", "Entered question uuid does not exist");
		}
		return question;
	}

	// Method to get answer Details based on answerID  #14
	public AnswerEntity findAnswerById(String answerId) throws AnswerNotFoundException {
		AnswerEntity answer = answerDao.findAnswerById(answerId);
		if (answer == null) {
			throw new AnswerNotFoundException("ANS-001", "Entered answer uuid does not exist");
		}
		return answer;
	}

	/*
		// Method to get question Details based on questionID  #15
		public QuestionEntity findQuestionAnswerById(String questionId) throws AuthorizationFailedException {
			QuestionEntity question = questionDao.findQuestionById(questionId);
			if (question == null) {
				throw new AuthorizationFailedException("QUES-001", "The question entered is invalid");
			}
			return question;
		}
	*/
	// Method to get answer Details based on questionID  #16
	public QuestionEntity findAllAnswersToQuestionId(String questionId) throws InvalidQuestionException {
		QuestionEntity question = questionDao.findQuestionById(questionId);
		if (question == null) {
			throw new InvalidQuestionException("QUES-001", "The question with entered uuid whose details are to be seen does not exist");
		}
		return question;
	}

}



