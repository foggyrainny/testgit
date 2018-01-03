import java.lang.reflect.Field;

public class Test {


      public static void main(String[] args){

         Class user = Users.class;

          String filedName="sex";

        Field[] fields=user.getDeclaredFields();
        boolean b=false;
        for(int i=0;i<fields.length;i++){

            if(fields[i].getName().equals(filedName)){
                b=true;
                break;
            }

        }
        if(b)
             System.out.println("users有该属性");
        else
             System.out.println("users没有该属性");



        }
}
