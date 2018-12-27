package come.liquan.mynotes.Actitities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import come.liquan.mynotes.R;

public class ConfirmActivity extends AppCompatActivity {
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("What!");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        findViewById(R.id.backok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                it.putExtra("res","backMore");
                setResult(RESULT_CANCELED,it);
                finish();
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }


}
