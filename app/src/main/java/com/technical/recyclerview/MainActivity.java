package com.technical.recyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    EditText No_of_rannges;
    public int FromeditTextCount=1;
    public int ToeditTextCount=1;
    public int TextViewCount=1;
    int no_of_ranges;
    LinearLayout myLayout;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //No_of_rannges=(EditText)findViewById(R.id.No_of_ranges);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Set Ranges");
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.mylayout);
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.RelativeLayout);
    }

    public void TakeAttendence(View view){
        startActivity(new Intent(this,Take_Attendence.class));
    }

    public void deleteView(){


        LinearLayout myLayout = (LinearLayout) findViewById(R.id.mylayout);
        int x = myLayout.getChildCount();

        if (x > 0) {
            myLayout.removeViewAt(x-1);
            myLayout.removeViewAt(x-2);
            myLayout.removeViewAt(x-3);
            --FromeditTextCount;
            --TextViewCount;
            --ToeditTextCount;
        }else{
            Toast.makeText(this,"No Subject to Delete" , Toast.LENGTH_SHORT).show();
        }



    }

    public void addRange(){
        try{

            LinearLayout myLayout = (LinearLayout) findViewById(R.id.mylayout);

      //  no_of_ranges = Integer.parseInt(No_of_rannges.getText().toString());


            TextView myTextView =new TextView(this);
            EditText myEditText_From = new EditText(this);
            EditText myEditText_To =new EditText(this);
            // Pass it an Activity or Context
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            myTextView.setLayoutParams(layoutParams);
            myTextView.setTextSize(20);
            myTextView.setText("Enter Range" + FromeditTextCount);
            myTextView.setPadding(20, 25, 20, 25);
            myTextView.setId(TextViewCount);
            layoutParams.setMargins(40, (10 ), 40, 20);
            myTextView.setBackgroundResource(R.drawable.edit);
            myLayout.addView(myTextView);

            myEditText_From.setLayoutParams(layoutParams);
            myEditText_From.setTextSize(20);
            myEditText_From.setHint("From");
            myEditText_From.setPadding(20, 25, 20, 25);
            myEditText_From.setId(FromeditTextCount);
            layoutParams.setMargins(40, (10 ), 40, 20);
            myEditText_From.setBackgroundResource(R.drawable.edit2);
            myLayout.addView(myEditText_From);

            myEditText_To.setLayoutParams(layoutParams);
            myEditText_To.setTextSize(20);
            myEditText_To.setHint("To");
            myEditText_To.setPadding(20, 25, 20, 25);
            myEditText_To.setId(ToeditTextCount);
            layoutParams.setMargins(40, (10 ), 40, 20);
            myEditText_To.setBackgroundResource(R.drawable.edit2);
            myLayout.addView(myEditText_To);


            FromeditTextCount++;
            TextViewCount++;
            ToeditTextCount++;


        }catch (NumberFormatException e){
            Toast.makeText(this,"Enter Valid Ranges",Toast.LENGTH_LONG).show();
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {

           addRange();
        }
        if (id==R.id.delete){
            deleteView();
        }

        return super.onOptionsItemSelected(item);
    }


}
