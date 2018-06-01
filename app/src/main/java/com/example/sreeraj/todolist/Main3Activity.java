package com.example.sreeraj.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Intent intent = getIntent();
        String heading = intent.getStringExtra("heading");
        String context = intent.getStringExtra("context");
        final int position = intent.getIntExtra("position", 0);
        final Button save = (Button) findViewById(R.id.button1);
        //final Button show = (Button) findViewById(R.id.button3);

        final Button delete = (Button) findViewById(R.id.button2);
        final EditText et1 = (EditText) findViewById(R.id.edittext1);
        final EditText et2 = (EditText) findViewById(R.id.edittext2);


        et1.setText(heading);
        et2.setText(context);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String heading = et1.getText().toString();
                String context = et2.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("todoinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String content_objects = sharedPreferences.getString("content_objects", "");

                Type type = new TypeToken<List<Content>>() {
                }.getType();
                Gson gson = new Gson();
                List<Content> contentList = new ArrayList<Content>();
                List<Content> demo = gson.fromJson(content_objects, type);
                if (!content_objects.matches("")) {
                    for (Content content : demo) {

                        contentList.add(content);
                    }
                }

                if(heading.matches("") && context.matches("")){
                    contentList.remove(position);}


                else{
                        contentList.get(position).context = context;

                       contentList.get(position).heading = heading;

                    }
                content_objects = gson.toJson(contentList);
                editor.putString("content_objects", content_objects);
                editor.commit();

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                if(heading.matches("") && context.matches("")){

                    Toast.makeText(Main3Activity.this, "Empty entry", Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(Main3Activity.this, "Saved", Toast.LENGTH_SHORT).show();}

                startActivity(intent);


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading = et1.getText().toString();
                String context = et2.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("todoinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String content_objects = sharedPreferences.getString("content_objects", "");

                Type type = new TypeToken<List<Content>>() {
                }.getType();
                Gson gson = new Gson();
                List<Content> contentList = new ArrayList<Content>();
                List<Content> demo = gson.fromJson(content_objects, type);
                if (!content_objects.matches("")) {
                    for (Content content : demo) {

                        contentList.add(content);
                    }
                }

                contentList.remove(position);
                content_objects = gson.toJson(contentList);
                editor.putString("content_objects", content_objects);
                editor.commit();

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                Toast.makeText(Main3Activity.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });


    }


}
