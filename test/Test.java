import com.school.repository.impl.BaseRepositotyImpl;
import com.school.util.CompareDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Test extends BaseRepositotyImpl {

    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    public static void main(String[] args) throws ParseException {


        Test test = new Test();
        test.SearchCourseCount(18230316);
    }



    public int SearchCourseCount(int stuid) {
        String sql ="select count(student_id) from s_selected_course where student_id =" + stuid;
        ResultSet query = query(sql);

        try {
            return query.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
