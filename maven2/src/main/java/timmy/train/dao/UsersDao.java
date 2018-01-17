package timmy.train.dao;

import timmy.train.domain.Users;

import java.util.List;

public interface UsersDao {

   public Users getUser(String  name);

   public void deleteUser(String  name);

   public List<Users> getUsersList(Users users);

   public void updateUser(String  name);

   public void saveUser(Users users);

}
