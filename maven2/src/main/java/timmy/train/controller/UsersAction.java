package timmy.train.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import timmy.train.domain.Users;
import timmy.train.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersAction extends ActionSupport {

    @Autowired
    private UsersService usersService;

    private HttpServletRequest request= ServletActionContext.getRequest();

    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public  String list(){
        List<Users> usersList=usersService.getUsersList(users);

         request.setAttribute("usersList",usersList);
        return "list";
    }
}
