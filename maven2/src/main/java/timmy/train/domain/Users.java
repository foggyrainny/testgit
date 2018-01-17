package timmy.train.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="name")
    private String name;//唯一的用户名
    @Column(name = "password")
    private String  password;//密码
    @Column(name = "repassword")
    private String  repassword;//确认密码
    @Column(name = "realname")
    private String realname;//真实姓名
    @Column(name = "idCard")
    private String idCard;//证件卡号
    @Column(name = "email")
    private String email;//邮件（非必填）
    @Column(name = "mobile")
    private String mobile;//手机号
    @Column(name="customerType")
    private int customerType;//游客类型
    @Column(name="Country")
    private int Country;//国籍
    @Column(name="idCardType")
    private int idCardType;//证件类型
    @Column(name = "sex")
    private int sex;//性别
    @Column(name = "birthday")
    private Date birthday;//生日


    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public int getCountry() {
        return Country;
    }

    public void setCountry(int country) {
        Country = country;
    }

    public int getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(int idCardType) {
        this.idCardType = idCardType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
