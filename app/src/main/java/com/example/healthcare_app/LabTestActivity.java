package com.example.healthcare_app;

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

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
        {" Package 1 :Full Body Checkup","","","","999"},   // this blank spaces bcz we will use multilines res. to represent that and we only two fields , 1st and last that why
        {" Package 2 : Blood Glucose Fasting","","","","299"},
        {" Package 3 : COVID-19 Antibody -IgG","","","","499"},
        {" Package 4 : Thyroid Check","","","","699"},
        {"  Package 5 : Immunity Check","","","","899"}
    };

    private String[] package_details = {

                "Blood Glucose Fasting\n" +
                        "Complete Hemogram\n" +
                        "HbA1c\n" +
                        "Iron Studies\n" +
                        "Kidney Function Test\n" +
                        "LDH lactate Dehydrogenase , Serum\n" +
                        "Lipid Profile\n" +
                        "Liver Function Test\n",
                "Blood Glucose Fasting",
                "COVID-19 Antibody -IgG",
                "Thyroid Profile-Total (T3, T4 & TSH Ultra- sensitive)",
                "Complete Hemogram\n" +
                        "CRC (C Reactive Protein) Quantitative, Serum\n" +
                        "Iron Studies\n" +
                        "Kidney Function Test\n" +
                        "Vitamin D Total-25 Hydroxy\n" +
                        "Liver Function Test\n" +
                        "Lipid Profile"

    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter SA;
    Button btnBack, btnGoToCart;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGoToCart = findViewById(R.id.buttonLTGoToCart);
        btnBack  = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < packages.length; i++){
            item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost: " +packages[i][4] + "/-");
            list.add(item);
        }

        SA = new SimpleAdapter(this,
                list,
                R.layout.multi_line,
                new String[]{"line1", "line2", "line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});


        listView.setAdapter(SA);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                            it.putExtra("text1",packages[positon][0]);
                            it.putExtra("text2",package_details[positon]);
                            it.putExtra("text3",packages[positon][4]);
                            startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}