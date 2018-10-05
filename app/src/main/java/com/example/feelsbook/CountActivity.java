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
    This will be called when press the count button. It will load the number of each emotions from file, and display them on the screen.
 */
package com.example.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CountActivity extends AppCompatActivity {
    private ArrayList<String> counts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        loadCount();
        Button backBtn = (Button)findViewById(R.id.button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(startIntent);
            }
        });
    }

    public void loadCount(){

        try {

            FileInputStream fileInputStream = openFileInput("count");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = bufferedReader.readLine();
            while( null != str )
            {
                counts.add(str);
                str = bufferedReader.readLine();
            }
            // show love count
            TextView count_love = (TextView) findViewById(R.id.Love_count);
            count_love.setText(counts.get(0));

            // show joy count
            TextView count_joy = (TextView) findViewById(R.id.Joy_count);
            count_joy.setText(counts.get(1));

            // show surprise count
            TextView count_surprise = (TextView) findViewById(R.id.Surprise_count);
            count_surprise.setText(counts.get(2));

            // show anger count
            TextView count_anger = (TextView) findViewById(R.id.Anger_count);
            count_anger.setText(counts.get(3));

            // show sadness count
            TextView count_sadness = (TextView) findViewById(R.id.Sadness_count);
            count_sadness.setText(counts.get(4));

            // show fear count
            TextView count_fear = (TextView) findViewById(R.id.Fear_count);
            count_fear.setText(counts.get(5));
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

    }
}
