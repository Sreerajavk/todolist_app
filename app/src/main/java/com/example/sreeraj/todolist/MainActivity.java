package com.example.sreeraj.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   String headings;
   String contexts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        //SharedPreferences contentObject = getSharedPreferences("todoinfo" , Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("todoinfo" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String content_objects = sharedPreferences.getString("content_objects","");



        TextView tv1 = (TextView)findViewById(R.id.textview1);
        TextView tv2 = (TextView)findViewById(R.id.textview2);
        TextView textView = (TextView)findViewById(R.id.textview);

        // String json = gson.toJson(contentList);
        //tv1.setText(json);
       // String content_string   = contentObject.getString("content_objects","");
       // Gson gson = new Gson();
       // Content obj = gson.fromJson(content_string , Content.class);
        Type type = new TypeToken<List<Content>>(){}.getType();
        Gson gson = new Gson();
        List<Content> contentList = new ArrayList<Content>();




        if(! content_objects.matches("")){


            contentList = gson.fromJson(content_objects , type);}





         if(contentList.size()!=0){
            if(contentList.size()==1)
                textView.setText("1 activity so far");
            else
                textView.setText(new String(contentList.size() + " activites so far"));}








        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        //recyclerView.setHasFixedSize(true);
        // set a LinearLayoutManager with default vertical orientaion
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        ProductAdapter customAdapter = new ProductAdapter(MainActivity.this,contentList );
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)

                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }
}
