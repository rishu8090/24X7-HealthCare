package com.example.healthcare_app.HealthActivityCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.LoginAndRegister.HomeActivity;
import com.example.healthcare_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details =
            {
                    {"Walking Daily","","","","Click for More Details"},
                    {"Home Care of COVID-19","","","","Click for More Details"},
                    {"Stop Smoking","","","","Click for More Details"},
                    {"Menstrual Cramps","","","","Click for More Details"},
                    {"HealthY Gut","","","","Click for More Details"}
            };

    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter SA;
    Button btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listViewHA);
        btnBack = findViewById(R.id.buttonHABack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < health_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }

        SA = new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(SA);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticleDetailsActivity.class);
                it.putExtra("text1", health_details[position][0]);
                it.putExtra("text2",images[position]);
                startActivity(it);
            }
        });
    }
}