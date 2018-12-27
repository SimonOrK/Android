package come.liquan.mynotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import come.liquan.mynotes.Actitities.MainActivity;

/**
 * Created by HP on 2018/12/22.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements View.OnClickListener{
    public List<book> lb=new ArrayList<>();
    private Listener listener;
    public void setListener(Listener listenerr){
      listener=listenerr;
    }
    public void setDeListener(View.OnClickListener listenerr){
        viewListener=listenerr;
    }
    public boolean RemoveForName(String name){
        book bk=null;
        for(book b:lb){
            if(b.name.equals(name)){
                bk=b;
                break;
            }
        }
        if(bk!=null){
            lb.remove(bk);
            return true;
        }
       return false;
    }
    public boolean addBook(book b){
        lb.add(b);
        return true;
    }
@Override
public void onClick(View view){
        if(this.listener!=null){
            listener.OnItemClick(view);
        }
}

static class ViewHolder extends RecyclerView.ViewHolder{
    ImageView book;
    TextView text;
    TextView Date;
    public ViewHolder(View view) {
        super(view);
        book = (ImageView) view.findViewById(R.id.bookImage);
        text = (TextView) view.findViewById(R.id.textView);
        Date=(TextView) view.findViewById(R.id.DateView);
    }
}
    View.OnClickListener viewListener;
    public BookAdapter(List<book> list){
        this.lb=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup father,int viewType){
        View view= LayoutInflater.from(father.getContext()).inflate(R.layout.book,father,false);
        view.setOnClickListener(BookAdapter.this);
        ViewHolder holder =new ViewHolder(view);
        view.findViewById(R.id.delete).setOnClickListener(viewListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        book bk=lb.get(position);
        holder.book.setImageResource(R.drawable.book);
        holder.text.setText("  "+bk.name);
        holder.Date.setText("创建日期 "+bk.Date+" ");
    }

    @Override
    public int getItemCount() {
        return lb.size();
    }
    public interface Listener{
        void OnItemClick(View view);
    }
}
