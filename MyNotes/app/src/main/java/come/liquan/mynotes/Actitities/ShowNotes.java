package come.liquan.mynotes.Actitities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import come.liquan.mynotes.DataBaseForNotes;
import come.liquan.mynotes.NotesAdapter;
import come.liquan.mynotes.R;
import come.liquan.mynotes.Utils;

public class ShowNotes extends AppCompatActivity implements View.OnClickListener,NotesAdapter.Listener{
    NotesAdapter na;
    String name;
   @Override
   public void onItemClick(View view){


       Intent intent=new Intent(this,ShowAllNotesActivity.class);
       intent.putExtra("text", ((TextView)view.findViewById(R.id.singleNotex)).getText());
       System.out.println( ((TextView)view.findViewById(R.id.singleNotex)).getText());
       intent.putExtra("bookName", name);
       intent.putExtra("editting","true");
       intent.putExtra("id",(int)view.getTag());
       startActivityForResult(intent,2);
   }
    @Override
    public void onClick(View view){
         if(view.getTag().equals("Back")){
          finish();
         }
         if(view.getTag().equals("Add")){
             Intent intent=new Intent(this,ShowAllNotesActivity.class);
             Intent intent1=getIntent();
             intent.putExtra("bookName",intent1.getStringExtra("bookName"));
             startActivityForResult(intent,1);
         }


    }
    @Override
    protected void onActivityResult(int req,int res,Intent in){
        switch(req) {
            case 1:if (res == RESULT_OK) {
                Cursor cur = DataBaseForNotes.getInstance().getWritableDatabase().query(name, null, null, null, null, null, null);
                na = new NotesAdapter(cur);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.NoteRec);
                recyclerView.setAdapter(na);
                cur.close();
                break;
            }
            case 2:if(res==RESULT_OK){
                Cursor cur = DataBaseForNotes.getInstance().getWritableDatabase().query(name, null, null, null, null, null, null);
                na = new NotesAdapter(cur);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.NoteRec);
                recyclerView.setAdapter(na);
                cur.close();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent intent=getIntent();
        name=intent.getStringExtra("bookName");
        Utils.ToastL("翻开了笔记本"+name);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.NoteRec);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        Cursor cur= DataBaseForNotes.getInstance().getWritableDatabase().query(name,null,null,null,null,null,null);
        na=new NotesAdapter(cur);
        na.setListner(this);
        recyclerView.setAdapter(na);
        findViewById(R.id.backx).setTag("Back");
        findViewById(R.id.backx).setOnClickListener(this);
        findViewById(R.id.addNote).setTag("Add");
        findViewById(R.id.addNote).setOnClickListener(this);
    }
}
