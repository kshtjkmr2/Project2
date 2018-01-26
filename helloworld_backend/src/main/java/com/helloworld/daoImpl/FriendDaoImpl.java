package com.helloworld.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.helloworld.dao.FriendDao;
import com.helloworld.model.Friend;
import com.helloworld.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
@Autowired
private SessionFactory sessionFactory;
	public List<User> getlistOfSuggestedUser(String username) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from user_helloworld where username in(Select username from user_helloworld where username!=? minus(Select fromId from friend_helloworld where toId=? and status!='D' union select toId from friend_helloworld where fromId=? and status!='D'))";
		SQLQuery sqlQuery=session.createSQLQuery(queryString);		
		sqlQuery.setString(0, username);
		sqlQuery.setString(1, username);
		sqlQuery.setString(2, username);
		sqlQuery.addEntity(User.class);
		List<User> suggestedUserList=sqlQuery.list();
		return suggestedUserList;
	}
	public void sendFriedRequest(String username, String toId) {
		Session session=sessionFactory.getCurrentSession();
		Friend friend=new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		session.save(friend);
	}
	public List<Friend> listOfPeendingRequest(String loogedInUser){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=? and status=?");
		query.setString(0, loogedInUser);
		query.setCharacter(1, 'P');
		List<Friend> pendingRequests=query.list();
		return pendingRequests;
	}
	public void updateRequest(Friend pendingRequest) {
		Session session=sessionFactory.getCurrentSession();
		session.update(pendingRequest);
		
	}
	public List<Friend> getFriendsList(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where(fromId=? or toId=?) and status=?");
		query.setString(0, username);
		query.setString(1, username);
		query.setCharacter(2, 'A');
		List<Friend> friends=query.list();
		return friends;
	}

}
