package dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mode.User;

public class UserDaoImpl extends BaseDaoImpl<User, Integer> {
	
public List<User> findUserById(int id){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("userId", id)).list();
	session.close();
	return list;
}
	
	
public List<User> findUserByEmail(String email){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("mail", email)).list();
	session.close();
	return list;
}

public List<User> findUserByEmailAndPass(String email,String pass){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("mail", email))
			.add(Restrictions.eq("password", pass)).list();
	session.close();
	return list;
}
public List<User> findAvaliableUser(String email,String pass){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("mail", email))
			.add(Restrictions.eq("password", pass))
			.add(Restrictions.eq("avaliable", true)).list();
	session.close();
	return list;
}
public List<User> findAvaliableSuperUser(String email,String pass){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("mail", email))
			.add(Restrictions.eq("password", pass))
			.add(Restrictions.eq("avaliable", true))
			.add(Restrictions.eq("role", 1)).list();
	session.close();
	return list;
}
/**
 * 返回所有该类状态的user数组
 * @return
 */
public List<User> listUserIsTreated(boolean isAvaliable){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq("avaliable", isAvaliable)).list();
	session.close();
	return list;
}
/**
 * 更新用户状态
 * @param id
 * @param isAvaliable
 * @return
 */
public boolean updateUserIsAvaliable(int id, boolean isAvaliable){
	Session session = getSession();
	Transaction trans=session.beginTransaction();
	String hql="update User u set u.avaliable="+
			isAvaliable+" where u.userId="+id;
Query q=session.createQuery(hql);
int ret = q.executeUpdate();
trans.commit();
session.close();
if (ret>0) {
	return true;
}
	return false;
}
public void updateUserInfo(User user){
	Session session = getSession();
	Transaction trans = session.beginTransaction();
	user.setAvaliable(user.getAvaliable());
	user.setMail(user.getMail());
	user.setUserName(user.getUserName());
	session.update(user);
	trans.commit();
	session.close();
	
}
public List<User> listUsers(){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.list();
	Collections.reverse(list);
	session.close();
	return list;
}

public List<User> findUserByTag(String name, Object value){
	List<User> list;
	Session session = getSession();
	Criteria criteria = session.createCriteria(User.class);
	list = criteria.add(Restrictions.eq(name, value)).list();
	session.close();
	return list;
}
public void setAuthority(User u, int category){
	Session session = getSession();
	
}
}
