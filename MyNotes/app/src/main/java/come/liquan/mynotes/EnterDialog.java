package come.liquan.mynotes;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import come.liquan.mynotes.Actitities.MainActivity;

/**
 * Created by HP on 2018/12/22.
 */

public class EnterDialog extends Dialog{
   public EnterDialog(Context context,int theme){
       super(context,theme);
       setContentView(R.layout.enter);
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
