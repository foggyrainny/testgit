package timmy.train.service;

import timmy.train.domain.Users;

import java.util.List;

public interface UsersService  {

    public Users getUser(String  name);

    public void removeUser(String  name);

    public List<Users> getUsersList(Users users);

    public void modifyUser(String  name);

    public void createUser(Users users);
}
