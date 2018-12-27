package come.liquan.mynotes;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by HP on 2018/12/23.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements View.OnClickListener {
    static class Note {
        String Date;
        String text;
        int Remind;
        int id;
    }
@Override
public void onClick(View view){
        if(this.listener!=null){
            listener.onItemClick(view);
        }
}
 public interface Listener{
       void onItemClick(View view);
 }
    ArrayList<Note> ln = new ArrayList<Note>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notes;
        TextView Date;
        TextView id;
        TextView remind;


    public ViewHolder(View view) {
        super(view);
        this.notes = (TextView) view.findViewById(R.id.singleNotex);
        this.Date = (TextView) view.findViewById(R.id.singleDate);
        this.remind = (TextView) view.findViewById(R.id.singleHS);
        this.id = (TextView) view.findViewById(R.id.singleId);
    }

    }
    Listener listener;
    public void setListner(Listener lins){
        this.listener=lins;
    }

    public NotesAdapter(Cursor cur){
     if(cur.moveToFirst()){
        do{
        Note note=new Note();
        note.Date=cur.getString(cur.getColumnIndex("Date"));//cur.getString(cur.getColumnIndex("Date"))
        note.Remind=cur.getInt(cur.getColumnIndex("Remind"));
        note.text=cur.getString(cur.getColumnIndex("Notes"));
        note.id=cur.getInt(cur.getColumnIndex("id"));
        ln.add(note);
        }while(cur.moveToNext());
    }
    cur.close();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note,parent,false);
        view.setOnClickListener(this);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note=ln.get(position);
        holder.notes.setText(note.text);
      if(note.Remind==1){
          holder.remind.setText("艾宾浩斯会提醒你的");
      }else{
          holder.remind.setText("不提醒");
      }
        holder.Date.setText(note.Date);
        holder.id.setText("id: "+note.id+"");
        holder.itemView.setTag(note.id);
    }
    @Override
    public int getItemCount() {
        return ln.size();
    }
}
