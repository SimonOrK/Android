package come.liquan.mynotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 2018/12/21.
 */

public class DataBaseForNotes extends SQLiteOpenHelper{
    public static boolean isExist(String name){
        Cursor cursor;
        cursor = getInstance().getWritableDatabase().rawQuery("select name from sqlite_master where type='table'", null);

        if(cursor.moveToFirst()){
            do{
                String name1 = cursor.getString(0);
                if(name1.equals(name))
                {

                    return  true;
                }
            }while(cursor.moveToNext());       }
            cursor.close();
        return false;
    }
    public static final String createPre="create table ";
    public static final String createCode=" ("
        +"id integer primary key autoincrement, "
        +"Notes text, "
        +"Date text, "
        +"Remind integer)";
    public volatile static DataBaseForNotes instance;
    public static DataBaseForNotes getInstance(){
        long l=System.currentTimeMillis();
        if(instance==null){
         instance=new DataBaseForNotes(Application.getContext(),"Books.db",null,1);

        }
        return instance;
    }
    public static final String BooksTab="create table Books (id integer primary key autoincrement, "
            + "Name text, "
            + "CreateDate text)"
            ;
    public static String getCreateStringOfTab(String BookName){
        return createPre+BookName+createCode;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BooksTab);
    }

    public DataBaseForNotes(Context context,String name,SQLiteDatabase.CursorFactory cursorFactory,int version){
      super(context,name,cursorFactory,version);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
