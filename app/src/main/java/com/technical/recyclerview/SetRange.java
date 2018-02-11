package com.technical.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SetRange extends AppCompatActivity {
    Toolbar toolbar;
    EditText No_of_rannges;
    public int FromeditTextCount=0;
    public int ToeditTextCount=0;
    public int TextViewCount=1;
    int no_of_ranges;
    LinearLayout myLayout;
    TextView myTextView ;
    EditText myEditText_From;
    EditText myEditText_To;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_range);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Set Ranges");


        myLayout = (LinearLayout) findViewById(R.id.mylayout);

    }


    public void TakeAttendence(View view)
    {
        Intent i=new Intent(this,Take_Attendence.class);

        int z = myLayout.getChildCount();

//Create the bundle
        Bundle bundle = new Bundle();

//Add your data to bundle
        bundle.putInt("getChild", z);

//Add the bundle to the intent
        i.putExtras(bundle);

        startActivity(i);
    }

    public void deleteView(){

        myLayout = (LinearLayout) findViewById(R.id.mylayout);
        int x = myLayout.getChildCount();
        Log.i("CHILD COUNT",""+x);

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

            myLayout = (LinearLayout) findViewById(R.id.mylayout);

            //  no_of_ranges = Integer.parseInt(No_of_rannges.getText().toString());


            myTextView =new TextView(this);
            myEditText_From = new EditText(this);
            myEditText_To =new EditText(this);
            // Pass it an Activity or Context
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            myTextView.setLayoutParams(layoutParams);
            myTextView.setTextSize(20);
            myTextView.setText("Enter Range" + (FromeditTextCount+1));
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
            myEditText_To .setId(ToeditTextCount);
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

    public void saveRange(){

//
//
//        try{
//
//
//            int x=myLayout.getChildCount();
//
//            int f=x-2;
//            int t=x-1;
//            int i=x/3;
//            int p=0;
//            for (p=0;(f-p)>=0;p=p+3) {

//                try {
//
//                    EditText e1 = (EditText) myLayout.getChildAt(f - p);
//                    EditText e2 = (EditText) myLayout.getChildAt(t - p);
//                    // int From = Integer.parseInt(e1.getText().toString());
//                    // int To = Integer.parseInt(e2.getText().toString());
//                    DatabaseReference mDatabase;
//
//                    mDatabase = FirebaseDatabase.getInstance().getReference();
//                    mDatabase.child("Range" + (i)).child("From").setValue(e1.getText().toString());
//                    mDatabase.child("Range" + (i)).child("To").setValue(e2.getText().toString());
//                    i--;
//                } catch (NumberFormatException e) {
//                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//                    Log.i("Exception Info", e.toString());
//
//                }
//
//            }
//
//
//
//            Toast.makeText(this, "Range Saved!!", Toast.LENGTH_SHORT).show();
//
//
//        }catch (NumberFormatException e){
//            Toast.makeText(this,"Enter Valid Ranges",Toast.LENGTH_LONG).show();
//        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.set_range_menu, menu);
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
        if (id == R.id.next) {


            saveRange();
        }

        return super.onOptionsItemSelected(item);
    }


}
