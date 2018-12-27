package come.liquan.mynotes;

import android.content.Context;
import android.view.View;

/**
 * Created by HP on 2018/12/24.
 */

public class Application extends android.app.Application {
static Context context;
    @Override
    public void onCreate(){
        super.onCreate();
     context=getApplicationContext();

    }
public static Context getContext(){
    return context;
}
}
