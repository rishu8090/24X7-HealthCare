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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] medicines =
    {
            {"Uprise-D3 1000IU Capsule","","","","50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule","","","","305"},
            {"Vitamin B Complex Capsules","","","","448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","538"},
            {"Dolo 650 Tablet","","","","30"},
            {"Crocin 650 Advance Tablet","","","","50"},
            {"Strepsils Medicated Lozenges for Sore Throat","","","","40"},
            {"Tata 1mg Calcium + Vitamin D3","","","","30"},
            {"Feronia -XT Tablet","","","","130"}
    };

    private String[] medicine_details =
            {"Building and keeping bones & teeth strong\n" +
                       "Reducing Fatique/stress & muscular pains\n" +
                       "Boosting immunity and increasing resistance against infection",
               "Chromium is an essential trace mineral that plays an important role in helping insulin regulatory",
               "Provide relief from vitamin B deficiencies\n" +
                       "Helps in formation of red blood cells\n" +
                       "Maintains healthy nervous system",
               "It promotes health as well as skin benefit.\n" +
                       "It helps reduce skin blemish and pigmentation\n" +
                       "It acts as safeguard the skin from the harsh UVA and UVB sun rays.",
               "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messages",
               "Helps relieve fever and bring down a high temperature\n" +
                       "Suitable for people with a heart condition or high blood pressure",
               "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                       "Provides a warm and comforting feeling during sore throat",
               "Reduce the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                       "Promotes mobility and flexibility of joints",
               "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
            };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter SA;
    ListView listView;
    Button btnBack, btnGotoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGotoCart = findViewById(R.id.buttonBMGoToCart);

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < medicines.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",medicines[i][0]);
            item.put("line2",medicines[i][1]);
            item.put("line3",medicines[i][2]);
            item.put("line4",medicines[i][3]);
            item.put("line5","Total Cost: ₹" + medicines[i][4] +"/-");
            list.add(item);
        }

        SA = new SimpleAdapter(this, list,
                R.layout.multi_line2,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        listView.setAdapter(SA);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", medicines[position][0]);
                it.putExtra("text2",medicine_details[position]);
                it.putExtra("text3", medicines[position][4]);
                startActivity(it);
            }
        });




    }
}