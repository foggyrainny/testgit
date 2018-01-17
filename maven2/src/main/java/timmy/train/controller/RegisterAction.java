package timmy.train.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import timmy.train.domain.Users;
import timmy.train.service.UsersService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends ActionSupport {

       @Autowired
       private UsersService usersService;

       HttpServletRequest request= ServletActionContext.getRequest();
       HttpServletResponse response=ServletActionContext.getResponse();
       private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public  String  selectOne(){

         String name= request.getParameter("name");
         Boolean result =false;
         if(StringUtils.isNotBlank(name)){

            if(null!=usersService.getUser(name)){
                    result=false;
            }else {
                result=true;
            }
         }

           return "result";
       }
}
