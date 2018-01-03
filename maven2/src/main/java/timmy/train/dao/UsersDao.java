package timmy.train.dao;

import timmy.train.domain.Users;

import java.util.List;

public interface UsersDao {

   public Users getUser(Users users);

   public void deleteUser(int id);

   public List<Users> getUsersList(Users users);

   public void addUser(Users users);

   public void updateUser(int id);

   public void saveUser(Users users);

}
