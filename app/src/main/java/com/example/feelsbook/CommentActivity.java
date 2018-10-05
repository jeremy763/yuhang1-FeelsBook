/*
Copyright (c) 2018 Yuhang(Jeremy) Xie

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 IN THE SOFTWARE.
 */

/*
    This class is called when click on any emotion button. It will ask for user's input, and save it in file.
 */
package com.example.feelsbook;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // set the title of the emotion
        TextView emotion = (TextView) findViewById(R.id.emotion);
        String text = getIntent().getStringExtra("emotion");
        emotion.setText(text);

        // init user input
         final EditText user_input = (EditText) findViewById(R.id.comment);


        // return to the main activity and pass in the message
        Button submitBtn = (Button)findViewById(R.id.Submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user input
                String comment = user_input.getText().toString();
                saveFile(comment);

                // show the indication
                Toast toast = Toast.makeText(CommentActivity.this, "Comments successfully added",Toast.LENGTH_LONG);
                toast.show();
                finish();
                // get back to main activity
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                //startActivity(intent);
            }
        });


        Button noBtn = (Button)findViewById(R.id.No_comment);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save a space in file when no comment button is clicked
                saveFile(" ");
                finish();
            }
        });
    }



    public void saveFile(String type) {
        String data = type;
        String file_name = "saved";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
            osw.append(data + " \n");

            osw.flush();
            osw.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
