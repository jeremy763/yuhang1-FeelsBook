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
    This is the main class. It will initialize the main interface of the app with all the buttons. load the number of emotions when it is created.
    Whenever a button is pressed, increase the number of that specific emotion by 1, save the emotion and current time in file. Save the number of
    emotions in another file.
 */

package com.example.feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    //ArrayList<String> counts = new ArrayList<>();
    private final String[] counts =new String[6];

    //final List<String> record = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCount();
        saveCount();



        // click on love
        Button loveBtn = (Button)findViewById(R.id.Love);
        loveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click love
                Integer index = Integer.valueOf(counts[0]);
                index = index + 1;
                String love = String.valueOf(index);
                counts[0] = love;
                // show the indication
                String code = "Love";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","LOVE");
                startActivity(startIntent);
            }
        });

        // click on joy
        Button joyBtn = (Button)findViewById(R.id.Joy);
        joyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click joy
                Integer index = Integer.valueOf(counts[1]);
                index = index + 1;
                String joy = String.valueOf(index);
                counts[1] = joy;
                // show the indication
                String code = "Joy";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","JOY");
                startActivity(startIntent);

            }
        });

        // click on surprise
        Button surpriseBtn = (Button)findViewById(R.id.Surprise_count);
        surpriseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click surprise
                Integer index = Integer.valueOf(counts[2]);
                index = index + 1;
                String surprise = String.valueOf(index);
                counts[2] = surprise;
                // show the indication
                String code = "Surprise";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","SURPRISE");
                startActivity(startIntent);
            }
        });

        // click on anger
        Button angerBtn = (Button)findViewById(R.id.Anger);
        angerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click anger
                Integer index = Integer.valueOf(counts[3]);
                index = index + 1;
                String anger = String.valueOf(index);
                counts[3] = anger;
                // show the indication
                String code = "Anger";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","ANGER");
                startActivity(startIntent);
            }
        });

        // click on sadness
        Button sadnessBtn = (Button)findViewById(R.id.Sadness);
        sadnessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click sadness
                Integer index = Integer.valueOf(counts[4]);
                index = index + 1;
                String sadness = String.valueOf(index);
                counts[4]= sadness;
                // show the indication
                String code = "Sadness";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","SADNESS");
                startActivity(startIntent);
            }
        });

        // click on fear
        Button fearBtn = (Button)findViewById(R.id.Fear);
        fearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever click love, the count_love ++
                Integer index = Integer.valueOf(counts[5]);
                index = index + 1;
                String fear = String.valueOf(index);
                counts[5] = fear;
                // show the indication
                String code = "Fear";
                saveFile(code);
                saveCount();
                Toast toast = Toast.makeText(MainActivity.this, "Emotion successfully added",Toast.LENGTH_SHORT);
                toast.show();

                // adding common in another activity
                Intent startIntent = new Intent(getApplicationContext(), CommentActivity.class );
                startIntent.putExtra("emotion","FEAR");
                startActivity(startIntent);
                //finishActivity(1);
            }
        });
        // whenever click on count, pass in all the data
        Button countBtn = (Button)findViewById(R.id.Count);
        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //saveCount(count_love, count_joy, count_surprise, count_anger, count_sadness, count_fear);
                //count = Integer.parseInt(count);
                Intent startIntent = new Intent(getApplicationContext(), CountActivity.class );
                //startIntent.putExtra("count_love",count_love);
                //startIntent.putExtra("count_joy",count_joy);
                //startIntent.putExtra("count_surprise",count_surprise);
                //startIntent.putExtra("count_anger",count_anger);
                //startIntent.putExtra("count_sadness",count_sadness);
                //startIntent.putExtra("count_fear",count_fear);
                startActivity(startIntent);
            }
        });

        // click on history
        Button historyBtn = (Button)findViewById(R.id.History);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), HistoryActivity.class );
                startActivity(startIntent);

            }
        });

    }

    public void saveFile(String type) {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss ");
        String dateToStr = format.format(today);
        String data = type;
        String file_name = "saved";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
            osw.append(data + "|" + dateToStr + "|");

            osw.flush();
            osw.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCount(){
        String file_name = "count";
        String count_love = counts[0];
        String count_joy = counts[1];
        String count_surprise = counts[2];
        String count_anger = counts[3];
        String count_sadness = counts[4];
        String count_fear = counts[5];
        String[] emotionValue = new String[] {count_love+"\n"+count_joy+"\n"+count_surprise+"\n"+count_anger+"\n"+count_sadness+"\n"+count_fear};
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            for (String s : emotionValue) {
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
            //ArrayList<String> items = new ArrayList<>();
            for (int i = 0; i < counts.length; i++)
            {
                counts[i] = str;
                str = bufferedReader.readLine();
            }
            /*
            count_love = Integer.valueOf(counts.get(0));
            count_joy = Integer.valueOf(counts.get(1));
            count_surprise = Integer.valueOf(counts.get(2));
            count_anger = Integer.valueOf(counts.get(3));
            count_sadness = Integer.valueOf(counts.get(4));
            count_fear = Integer.valueOf(counts.get(5));
                */

        } catch (FileNotFoundException e) {
            counts[0] = "0";
            counts[1] = "0";
            counts[2] = "0";
            counts[3] = "0";
            counts[4] = "0";
            counts[5] = "0";

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


