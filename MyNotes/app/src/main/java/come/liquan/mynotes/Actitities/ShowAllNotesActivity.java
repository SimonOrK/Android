package come.liquan.mynotes.Actitities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import come.liquan.mynotes.DataBaseForNotes;
import come.liquan.mynotes.R;
import come.liquan.mynotes.Utils;
import come.liquan.mynotes.book;

public class ShowAllNotesActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    public void onClick(View view){
        Intent intent=new Intent(ShowAllNotesActivity.this,ConfirmActivity.class);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int request, int result, Intent intent){
        if(request==1) {
            if(intent!=null) {
                if ("backMore".equals(intent.getStringExtra("res"))) {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.notes_title));
        final Intent intent=getIntent();
        final String bookName=intent.getStringExtra("bookName");
        final TextView textc= (TextView) findViewById(R.id.input);
        final CheckBox c=(CheckBox)findViewById(R.id.checkBox);
        if("true".equals(intent.getStringExtra("editting"))) {
            textc.setText(intent.getStringExtra("text"));
        }
        findViewById(R.id.OK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=textc.getText().toString();
                ContentValues cv=new ContentValues();
                cv.put("Notes",str);
                cv.put("Date", Utils.getNowTime());
                if(c.isChecked()) {
                    cv.put("Remind",1);
                }else{
                    cv.put("Remind",0);
                }
                if("true".equals(intent.getStringExtra("editting"))){
                    DataBaseForNotes.getInstance().getWritableDatabase().update(bookName,cv,"id = ?",new String[]{intent.getIntExtra("id",0)+""});
                }else {
                    DataBaseForNotes.getInstance().getWritableDatabase().insert(bookName, null, cv);
                }
                Utils.ToastS("保存成功!");
                setResult(RESULT_OK);
                finish();
            }
        });
        findViewById(R.id.backToNotes).setOnClickListener(this);

    }
}

