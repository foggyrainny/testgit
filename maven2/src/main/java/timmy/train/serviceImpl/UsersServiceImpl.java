package timmy.train.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timmy.train.dao.UsersDao;
import timmy.train.domain.Users;
import timmy.train.service.UsersService;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersDao usersDao;

    public Users getUser(String name) {

        return   usersDao.getUser(name);
    }

    public void removeUser(String name) {
         usersDao.deleteUser(name);
    }

    public List<Users> getUsersList(Users users) {
        return usersDao.getUsersList(users);
    }

    public void modifyUser(String name) {
       usersDao.updateUser(name);
    }

    public void createUser(Users users) {
      usersDao.saveUser(users);
    }
}
