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
import android.view.LayoutInflater;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText No_of_rannges;
    public int FromeditTextCount=0;
    public int ToeditTextCount=0;
    public int TextViewCount=1;
    int no_of_ranges;
    LinearLayout myLayout;
    TextView myTextView ;
    EditText Section_Name;
    EditText myEditText_To;
    DBHelper dbHelper;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Set Sections");

        dbHelper = new DBHelper(this);

    }

    public void SetRange(View view)
    {
        Intent i=new Intent(this,SetRange.class);

        startActivity(i);
    }

    public void addSections(){
        try{

            myLayout = (LinearLayout) findViewById(R.id.mylayout);

            //  no_of_ranges = Integer.parseInt(No_of_rannges.getText().toString());


            myTextView =new TextView(this);
            Section_Name = new EditText(this);
            myEditText_To =new EditText(this);
            // Pass it an Activity or Context
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            myTextView.setLayoutParams(layoutParams);
            myTextView.setTextSize(20);
            myTextView.setText("Enter Section" + (FromeditTextCount+1));
            myTextView.setPadding(20, 25, 20, 25);
            myTextView.setId(TextViewCount);
            layoutParams.setMargins(40, (10 ), 40, 20);
            myTextView.setBackgroundResource(R.drawable.edit);
            myLayout.addView(myTextView);

            Section_Name.setLayoutParams(layoutParams);
            Section_Name.setTextSize(20);
            Section_Name.setHint("Section Name");
            Section_Name.setPadding(20, 25, 20, 25);
            Section_Name.setId(FromeditTextCount);
            layoutParams.setMargins(40, (10 ), 40, 20);
            Section_Name.setBackgroundResource(R.drawable.edit2);
            myLayout.addView(Section_Name);

            FromeditTextCount++;
            TextViewCount++;



        }catch (NumberFormatException e){
            Toast.makeText(this,"Enter Valid Ranges",Toast.LENGTH_LONG).show();
        }


    }
    public void deleteSections(){

        myLayout = (LinearLayout) findViewById(R.id.mylayout);
        int x = myLayout.getChildCount();
        Log.i("CHILD COUNT",""+x);

        if (x > 0) {
            myLayout.removeViewAt(x-1);
            myLayout.removeViewAt(x-2);




            --FromeditTextCount;
            --TextViewCount;

        }else{
            Toast.makeText(this,"No Subject to Delete" , Toast.LENGTH_SHORT).show();
        }
    }

    public void saveSections(){

        int x = myLayout.getChildCount();

        for (int p=1;x-p>=0;p=p+2) {
            EditText value = (EditText) myLayout.getChildAt(x - p);
            Log.i("Section Names", value.getText().toString());


            if (value.getText().toString() != "") {

                dbHelper.addSection(value.getText().toString());

                dbHelper.CreateRange(value.getText().toString());

                Toast.makeText(this, "Sections Saved!!", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Enter Valid Details!!", Toast.LENGTH_LONG).show();
            }

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
        if(id==R.id.add){

            addSections();
        }
        if(id==R.id.delete){

            deleteSections();
        }
        if (id==R.id.Save){
           saveSections();
        }


        return super.onOptionsItemSelected(item);
    }


}
