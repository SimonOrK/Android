package come.liquan.mynotes.Actitities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import come.liquan.mynotes.Application;
import come.liquan.mynotes.BookAdapter;
import come.liquan.mynotes.DataBaseForNotes;
import come.liquan.mynotes.DeleteDialog;
import come.liquan.mynotes.R;
import come.liquan.mynotes.Utils;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final  DeleteDialog deleteDialog=new DeleteDialog(this,R.style.Custom);
        deleteDialog.setContentView(R.layout.delete);
        deleteDialog.show();
        Button but=(Button)deleteDialog.findViewById(R.id.deleteButton);
        final EditText et=(EditText) deleteDialog.findViewById(R.id.deleteText);
        final String bookName=getIntent().getStringExtra("bookName");

        but.setText("再见了! "+bookName);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et.getText().toString().equalsIgnoreCase("bye")){
                    //System.out.println("@"+bookName+"@");
                    //System.out.println("!"+DataBaseForNotes.isExist(bookName)+"!");
                    DataBaseForNotes.getInstance().getWritableDatabase().execSQL("drop table if exists "+bookName);
                    DataBaseForNotes.getInstance().getWritableDatabase().delete("Books","Name = ?",new String[]{bookName});
                    Intent intent=new Intent();
                    intent.putExtra("bookName",bookName);
                    setResult(RESULT_OK,intent);
                    deleteDialog.dismiss();
                    finish();
                }
            }
        });
    }
}
