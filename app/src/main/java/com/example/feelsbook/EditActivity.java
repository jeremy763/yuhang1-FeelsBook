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
    This class is called when press the edit on the dialog. It will load the text from file to a editText, so that user could edit it. When the user finished edit and press submit, it will get the user's input,
    check if the emotion is changed. If yes, decrease the number of old emotion by 1,and increase the new emotion by 1. Save the new data in file, and save the new nubmer of emotions in file.
 */
package com.example.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EditActivity extends AppCompatActivity {
    private ArrayList<String> info = new ArrayList<>();
    private int count_love;
    private int count_joy;
    private int count_surprise;
    private int count_anger;
    private int count_sadness;
    private int count_fear;
    private Integer position;
    private ArrayList<String> countEmotion = new ArrayList<>();
    private String original = "";
    private String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        loadCount();
        readFile();

        // load the data that need to be edited
        EditText user_input = (EditText) findViewById(R.id.editText);
        String text = getIntent().getStringExtra("edit");
        position = getIntent().getIntExtra("position",1);
        user_input.setText(text);
        // set original
        getEmotion();
        info.remove(info.get(position));

        // when submit button is pressed
        Button submitBtn = (Button)findViewById(R.id.subedit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user input
                EditText user_input = (EditText) findViewById(R.id.editText);
                String changes= user_input.getText().toString();
                // check the emotion
                String[] count = changes.split("");
                char symbol = '|';
                String s = Character.toString(symbol);

                // get the emotion in a string
                for (int i= 0;i < count.length; i++) {

                    if (! count[i].equals(s)) {
                        current = current + count[i];

                    }else{
                        break;
                    }
                }
                // check if the emotion has changed
                if (! current.equals(original)){
                    updateCount(original);
                }
                // save changes to file
                info.add(changes);
                Collections.rotate(info,1);
                // show the indication
                Toast toast = Toast.makeText(EditActivity.this, "successfully submitted",Toast.LENGTH_LONG);
                toast.show();
                Intent startIntent = new Intent(getApplicationContext(), HistoryActivity.class );
                startActivity(startIntent);
                saveFile();

            }
        });

    }
    public void readFile(){

        try {

            FileInputStream fileInputStream = openFileInput("saved");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = bufferedReader.readLine();
            while (null != str) {
                info.add(str);
                str = bufferedReader.readLine();
            }
        }catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        String file_name = "saved";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
            for (int i= 0;i < info.size(); i++) {
                osw.append(info.get(i) + "\n");
            }
            osw.flush();
            osw.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCount() {
        try {
            FileInputStream fileInputStream = openFileInput("count");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = bufferedReader.readLine();
            while( null != str )
            {
                countEmotion.add(str);
                str = bufferedReader.readLine();
            }
            count_love = Integer.valueOf(countEmotion.get(0));
            count_joy = Integer.valueOf(countEmotion.get(1));
            count_surprise = Integer.valueOf(countEmotion.get(2));
            count_anger = Integer.valueOf(countEmotion.get(3));
            count_sadness = Integer.valueOf(countEmotion.get(4));
            count_fear = Integer.valueOf(countEmotion.get(5));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEmotion(){
        String[] count = info.get(position).split("");

        char symbol = '|';
        String s = Character.toString(symbol);

        // get the emotion in a string
        for (int i= 0;i < count.length; i++) {

            if (! count[i].equals(s)) {
                original = original + count[i];

            }else{
                break;
            }
        }
    }

    public void updateCount(String original){
        if (original.equals("Love")){
            count_love = count_love -1;
            setCurrent();
        }
        if (original.equals("Surprise")){
            count_surprise = count_surprise -1;
            setCurrent();
        }
        if (original.equals("Anger")){
            count_anger = count_anger -1;
            setCurrent();

        }
        if (original.equals("Sadness")){
            count_sadness = count_sadness -1;
            setCurrent();
        }
        if (original.equals("Joy")){
            count_joy = count_joy -1;
            setCurrent();
        }
        if (original.equals("Fear")){
            count_fear = count_fear -1;
            setCurrent();
        }

        String love1 = String.valueOf(count_love);
        String joy1 = String.valueOf(count_joy);
        String surprise1 = String.valueOf(count_surprise);
        String anger1 = String.valueOf(count_anger);
        String sadness1 = String.valueOf(count_sadness);
        String fear1 = String.valueOf(count_fear);

        String[] saveCount = new String[] {love1+"\n"+joy1+"\n"+surprise1+"\n"+anger1+"\n"+sadness1+"\n"+fear1};
        try {
            FileOutputStream fileOutputStream = openFileOutput("count", MODE_PRIVATE);
            for (String s : saveCount) {
                fileOutputStream.write(s.getBytes());
            }
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrent(){
        if (current.equals("Love")){
            count_love = count_love +1;

        }
        if (current.equals("Surprise")){
            count_surprise = count_surprise +1;
        }
        if (current.equals("Anger")){
            count_anger = count_anger +1;
        }
        if (current.equals("Sadness")){
            count_sadness = count_sadness +1;
        }
        if (current.equals("Joy")){
            count_joy = count_joy +1;
        }
        if (current.equals("Fear")){
            count_fear = count_fear +1;
        }
    }
}
