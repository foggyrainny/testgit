package timmy.train.domain;


import javax.persistence.Entity;

@Entity
public class Student extends Users {

    private int no;//学号

    private String SchoolName;//学校名称

    private String  province;//学校省份

    private String college;//院系（非必填）

    private String classNumber;//班级（非必填）

    private int syear;//入学年份

    private int syears;//学制

    private String pcard;//优惠卡号

    private String pSection1;//优惠区间1

    private String pSection2;//优惠区间2


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public int getSyear() {
        return syear;
    }

    public void setSyear(int syear) {
        this.syear = syear;
    }

    public int getSyears() {
        return syears;
    }

    public void setSyears(int syears) {
        this.syears = syears;
    }

    public String getPcard() {
        return pcard;
    }

    public void setPcard(String pcard) {
        this.pcard = pcard;
    }

    public String getpSection1() {
        return pSection1;
    }

    public void setpSection1(String pSection1) {
        this.pSection1 = pSection1;
    }

    public String getpSection2() {
        return pSection2;
    }

    public void setpSection2(String pSection2) {
        this.pSection2 = pSection2;
    }
}
