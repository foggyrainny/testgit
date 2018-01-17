package timmy.train.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import timmy.train.dao.UsersDao;
import timmy.train.domain.Users;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Repository
public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao{

   @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Transaction transaction;

    public Users getUser(String name) {
           Users users= (Users) sessionFactory.openSession().get(Users.class,name);
           sessionFactory.openSession().close();
        return users;
    }

    public void deleteUser(String  name) {

        transaction=sessionFactory.openSession().beginTransaction();
        sessionFactory.openSession().delete(
                sessionFactory.openSession().get(Users.class,name));
        transaction.commit();
        sessionFactory.openSession().close();
    }
    //待解决：查询列表出错 --已解决（代码书写时报的错）
    public List<Users> getUsersList(Users users) {
         System.out.println(sessionFactory);
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from Users");
        List<Users> usersList=query.list();
        sessionFactory.openSession().close();
        return usersList;
    }


    public void updateUser(String  name) {
        transaction= sessionFactory.openSession().beginTransaction();
        sessionFactory.openSession().update(Users.class,name);
        transaction.commit();
        sessionFactory.openSession().close();
    }

    public void saveUser(Users users) {

        Session session=sessionFactory.openSession();

        transaction=session.beginTransaction();
        session.save(users);
        transaction.commit();
        sessionFactory.openSession().close();
    }

      public static void main(String[] args){
          UsersDaoImpl usersDao=new UsersDaoImpl();
//          Users users=usersDao.getUser("lili");
 //         usersDao.updateUser("lili");
          Users users=new Users();
          users.setName("lili");
          users.setSex(1);
          users.setCountry(1);
          users.setBirthday(new Date());
          users.setCustomerType(1);
          users.setIdCard("341225199203277017");
          users.setMobile("13033037363");
          users.setPassword("123456");
          users.setIdCardType(1);
          usersDao.saveUser(users);
        }
}
