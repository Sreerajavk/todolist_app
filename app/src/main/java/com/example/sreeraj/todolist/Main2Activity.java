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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main2Activity extends AppCompatActivity {

    ArrayList<String> headings = new ArrayList<String>();

    String heading_list_string="";
    String context_list_string ="";
    String content_objects;
    String text = "";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button bt1 = (Button)findViewById(R.id.button1);


        final Button bt2 = (Button)findViewById(R.id.button2);
        final EditText et1 = (EditText)findViewById(R.id.edittext1);
        final EditText et2 = (EditText)findViewById(R.id.edittext2);

        SharedPreferences sharedPreferences = getSharedPreferences("todoinfo" , Context.MODE_PRIVATE);

        content_objects = sharedPreferences.getString("content_objects","");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String heading =  et1.getText().toString();
                String context = et2.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("todoinfo" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                content_objects = sharedPreferences.getString("content_objects","");

                Type type = new TypeToken<List<Content>>(){}.getType();
                Gson gson = new Gson();
                List<Content> contentList = new ArrayList<Content>();
                if(! (heading.matches("") && context.matches(""))){
                Content obj= new Content(heading,context);
                contentList.add(obj);
                text = "Saved";}
                else{
                    text = "empty entry";
                }



                 List<Content> demo = gson.fromJson(content_objects , type);
                 if(!content_objects.matches("")){
                 for(Content content : demo){

                     contentList.add(content);
                 }}

                content_objects = gson.toJson(contentList);
                editor.putString("content_objects",content_objects);
                editor.commit();


                Intent intent = new Intent(Main2Activity.this , MainActivity.class);

                Toast.makeText(Main2Activity.this , text , Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();










            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText("");
                et2.setText("");
            }
        });


    }
}
