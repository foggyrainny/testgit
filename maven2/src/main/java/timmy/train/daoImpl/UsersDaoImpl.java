package timmy.train.daoImpl;

import timmy.train.dao.UsersDao;
import timmy.train.domain.Users;

import java.util.List;

public class UsersDaoImpl implements UsersDao{


    public Users getUser(Users users) {
        String hql="from users where id=?";
        return null;
    }

    public void deleteUser(int id) {

    }

    public List<Users> getUsersList(Users users) {
        return null;
    }

    public void addUser(Users users) {

    }

    public void updateUser(int id) {

    }

    public void saveUser(Users users) {

    }
}
