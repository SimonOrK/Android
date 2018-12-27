package come.liquan.mynotes.Actitities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.sip.SipAudioCall;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import come.liquan.mynotes.Application;
import come.liquan.mynotes.BookAdapter;
import come.liquan.mynotes.DataBaseForNotes;
import come.liquan.mynotes.DeleteDialog;
import come.liquan.mynotes.NotesAdapter;
import come.liquan.mynotes.R;
import come.liquan.mynotes.Utils;
import come.liquan.mynotes.book;

public class MainActivity extends AppCompatActivity implements BookAdapter.Listener,View.OnClickListener{
    NotesAdapter ns;
    BookAdapter ba;
//    public static BookAdapter getAd(){
//        return ad;
//    }
//    public static void Toast(String str,int io){
//    Toast.makeText(this,str,io).show();
//    }
//    public static DataBaseForNotes getDB(){
//        return this.db;
//    }
//    public static View find(int i){
//        return findViewById(i);
//    }
//    public static void setLayOut(int i){
//        setContentView(i);
//    }
    @Override
    public void onClick(View view){
        Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
        View v1=(View) view.getParent();

        intent.putExtra("bookName",((TextView)v1.findViewById(R.id.textView)).getText().toString().replace(" ",""));
        startActivityForResult(intent,DeleteBook);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.Toolbutton));
        DataBaseForNotes da=DataBaseForNotes.getInstance();
        Cursor cur=null;
        if(da!=null){
            cur=da.getWritableDatabase().query("Books",null,null,null,null,null,null);
        }
       ArrayList<book> ab=new ArrayList<>();
        if(cur!=null){
            if(cur.moveToFirst()){
                do{
                    String bookName=cur.getString(cur.getColumnIndex("Name"));
                    String createDate=cur.getString(cur.getColumnIndex("CreateDate"));
                    ab.add(new book(bookName,createDate));
                }while(cur.moveToNext());
            }
            cur.close();
        }
        ba=new BookAdapter(ab);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(ba);
        ba.setListener(this);
        ba.setDeListener(this);
     }

//    public static void showNotes(Cursor cur,String name){
//
//        mainActivity.setContentView(R.layout.activity_note);
//        Toast.makeText(mainActivity,"翻开了笔记本"+name,Toast.LENGTH_LONG).show();
//        RecyclerView recyclerView=(RecyclerView)mainActivity.findViewById(R.id.NoteRec);
//        LinearLayoutManager manager=new LinearLayoutManager(mainActivity);
//        recyclerView.setLayoutManager(manager);
//        NotesAdapter na=new NotesAdapter(cur);
////        recyclerView.setAdapter(na);
//    }
    @Override
    public void OnItemClick(View view){
                TextView text=(TextView)view.findViewById(R.id.textView);
                String bookName=text.getText().toString().replace(" ","");
                Intent intent=new Intent(this,ShowNotes.class);
                intent.putExtra("bookName",bookName);
                startActivity(intent);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }
//    public static void showMenu(){
//        mainActivity.setSupportActionBar((Toolbar) mainActivity.findViewById(R.id.Toolbutton));
//        mainActivity.openOptionsMenu();
//    }
    public void update(){
        RecyclerView recyclerView=(RecyclerView)this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(ba);
    }
    static int CreateBook=1;
    static int DeleteBook=2;
    @Override
    protected void onActivityResult(int request, int result, Intent intent){
        if(request==CreateBook){
            if(result==RESULT_OK){

                book b=new book(intent.getStringExtra("bookName"),intent.getStringExtra("Date"));


                ba.addBook(b);
                update();
            }
        }
        if(request==DeleteBook){
            if(result==RESULT_OK){
               ba.RemoveForName(intent.getStringExtra("bookName"));
               System.out.println(intent.getStringExtra("bookName")+" while");
                update();
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.add:
            {
               Intent intent=new Intent(this,NotesActivity.class);
                startActivityForResult(intent,CreateBook);
            }

        }
        return true;
    }
}
