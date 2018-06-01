package com.example.sreeraj.todolist;

import java.lang.ref.SoftReference;

/**
 * Created by sreeraj on 31/5/18.
 */

class Content {


    String heading;
    String context;
    Content(String heading , String context){
        this.heading = heading;
        this.context = context;
    }
    public String getHeading(){
        return heading;
    }
    public String getContext(){
        return context;
    }
}
