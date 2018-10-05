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
    This is called when press on the History button. It will load the records from file, and put them in the listView. Whenever deleteData is called, it will delete the current data,
    and decrease the number of the emotion by 1. Save the changes of nubmer of emotion in file. And save the current records in file. Refresh the activity afterwards.
 */
package com.example.feelsbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.ScriptGroup;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements exmaple_dialog.ExampleDialogListener {
    ListView History_list;
    private ArrayList<String> strings = new ArrayList<>();
    private int pos;
    private String count_emotion = "";
    private ArrayList<String> countEmotion = new ArrayList<>();
    private int count_love;
    private int count_joy;
    private int count_surprise;
    private int count_anger;
    private int count_sadness;
    private int count_fear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // get the user input
        loadCount();
        readInfo();

        //get the information in a dialog


        Button delBtn = (Button)findViewById(R.id.delete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(startIntent);

            }
        });


    }


    public void readInfo(){

        try {

            FileInputStream fileInputStream = openFileInput("saved");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = bufferedReader.readLine();
            while( null != str )
            {
                strings.add(str);
                str = bufferedReader.readLine();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(String s: strings){
                stringBuilder.append(s);
                stringBuilder.append(",");
            }



            History_list = (ListView) findViewById(R.id.History_list);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    strings);

            History_list.setAdapter(adapter);

            History_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    pos = position;
                    exmaple_dialog Exmaple_dialog = new exmaple_dialog();
                    Exmaple_dialog.show(getSupportFragmentManager(),"E");
                }
            });



        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteData(){
        // put it in list
        String[] count = strings.get(pos).split("");

        char symbol = '|';
        String s = Character.toString(symbol);

        // get the emotion in a string
        for (int i= 0;i < count.length; i++) {

            if (! count[i].equals(s)) {
                count_emotion = count_emotion + count[i];

            }else{
                break;
            }
        }
        saveCount(count_emotion);
        strings.remove(pos);

        saveFile(strings);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void saveFile(ArrayList<String> items) {
        String file_name = "saved";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
            for (int i= 0;i < items.size(); i++) {
                osw.append(items.get(i) + "\n");
            }
            osw.flush();
            osw.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCount(String update){
        String file_name = "count";
        if (update.equals("Love")){
            count_love = count_love -1;
        }
        if (update.equals("Surprise")){
            count_surprise = count_surprise -1;
        }
        if (update.equals("Anger")){
            count_anger = count_anger -1;
        }
        if (update.equals("Sadness")){
            count_sadness = count_sadness -1;
        }
        if (update.equals("Joy")){
            count_joy = count_joy -1;
        }
        if (update.equals("Fear")){
            count_fear = count_fear -1;
        }

        String love1 = String.valueOf(count_love);
        String joy1 = String.valueOf(count_joy);
        String surprise1 = String.valueOf(count_surprise);
        String anger1 = String.valueOf(count_anger);
        String sadness1 = String.valueOf(count_sadness);
        String fear1 = String.valueOf(count_fear);

        String[] saveCount = new String[] {love1+"\n"+joy1+"\n"+surprise1+"\n"+anger1+"\n"+sadness1+"\n"+fear1};
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            for (String s : saveCount) {
                fileOutputStream.write(s.getBytes());
            }
            fileOutputStream.close();
        } catch (Exception e) {
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

    @Override
    public void editData() {
        Intent startIntent = new Intent(getApplicationContext(), EditActivity.class );
        startIntent.putExtra("edit",strings.get(pos));
        startIntent.putExtra("position",pos);
        startActivity(startIntent);

    }
}
