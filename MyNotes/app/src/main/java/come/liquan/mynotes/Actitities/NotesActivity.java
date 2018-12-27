package come.liquan.mynotes.Actitities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import come.liquan.mynotes.Application;
import come.liquan.mynotes.DataBaseForNotes;
import come.liquan.mynotes.EnterDialog;
import come.liquan.mynotes.R;
import come.liquan.mynotes.Utils;

public class NotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EnterDialog en=new EnterDialog(this,R.style.Custom);

        en.show();
        Button but=(Button)en.findViewById(R.id.EnterButton);
        final EditText et=(EditText) en.findViewById(R.id.enterText);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    if(!DataBaseForNotes.isExist(et.getText().toString())){
                if (et.getText().toString().length() < 18) {
                    if (et.getText().toString().length() > 0) {
                        SQLiteDatabase db = DataBaseForNotes.getInstance().getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("Name", et.getText().toString());
                        String str = Utils.getNowTime();
                        cv.put("CreateDate", str);
                        db.insert("Books", null, cv);
                        db.execSQL(DataBaseForNotes.getCreateStringOfTab(et.getText().toString()));

                        en.dismiss();
                        Utils.ToastS("哗啦啦,空白字典本已装订入库!");
                        Intent intent = new Intent();
                        intent.putExtra("bookName", et.getText().toString());
                        intent.putExtra("Date", str);

                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        {
                            Utils.ToastL("书名不能为空!");
                        }
                    }
                } else {
                    Utils.ToastL("书名太长了!总长9个汉字以内或18个字母以内!");
                }
            }else{
                Utils.ToastL("已经有这本书了!");
            }
                }


        });
    }
}
