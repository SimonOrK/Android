package come.liquan.mynotes;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP on 2018/12/22.
 */

public class Utils {
    public static String getNowTime(){
        Calendar ar=Calendar.getInstance();
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sd.format(new Date());
    }
    public static void ToastS(String str){
        Toast.makeText(Application.getContext(),str,Toast.LENGTH_SHORT);
    }
    public static void ToastL(String str){
        Toast.makeText(Application.getContext(),str,Toast.LENGTH_LONG);
    }
}
