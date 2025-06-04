package com.example.healthcare_app.DoctorsCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doc. Name : Dr. Suraj Nagar", "Hospital Address : Zafraabaad", "Experience : 5yrs", "Mob No. : 8799549564", "600"},
                    {"Doc. Name : Dr. Satish ", "Hospital Address : Jp Colony", "Experience : 8yrs", "Mob No. : 8543535344", "900"},
                    {"Doc. Name : Dr. Sachin Nishad", "Hospital Address : Kutana", "Experience : 3yrs", "Mob No. : 8720506821", "300"},
                    {"Doc. Name : Dr. Pappu", "Hospital Address : Sainik Colony", "Experience : 4yrs", "Mob No. : 9489249499", "700"},
                    {"Doc. Name : Dr. Rohit Mehra", "Hospital Address : Jaddu Town", "Experience : 2yrs", "Mob No. : 7858939302", "400"}
            };

    private String[][] doctor_details2 =
            {
                    {"Doc. Name : Dr. Divyansh", "Hospital Address : Karol Bagh", "Experience : 4yrs", "Mob No. : 8799549564", "500"},
                    {"Doc. Name : Dr. Anuj Pal ", "Hospital Address : Kanpur", "Experience : 3yrs", "Mob No. : 8543535344", "400"},
                    {"Doc. Name : Dr. Vikas Kaushik", "Hospital Address : Palwal", "Experience : 5yrs", "Mob No. : 8720506821", "500"},
                    {"Doc. Name : Dr. Abhishek Rawat", "Hospital Address : Faridabaad", "Experience : 6yrs", "Mob No. : 9489249499", "700"},
                    {"Doc. Name : Dr. Aditya Panchal", "Hospital Address : Panipat", "Experience : 2yrs", "Mob No. : 7858939302", "300"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doc. Name : Dr. Saurabh", "Hospital Address : Kutana", "Experience : 2yrs", "Mob No. : 8799549564", "200"},
                    {"Doc. Name : Dr. Shalu ", "Hospital Address : Kutana", "Experience : 4yrs", "Mob No. : 8543535344", "600"},
                    {"Doc. Name : Dr. Komal Makker", "Hospital Address : Palika Bazaar", "Experience : 10yrs", "Mob No. : 8720506821", "1000"},
                    {"Doc. Name : Dr. S. Janki", "Hospital Address : Hydrabaad", "Experience : 3yrs", "Mob No. : 9489249499", "800"},
                    {"Doc. Name : Dr. Bala", "Hospital Address : China Town", "Experience : 9yrs", "Mob No. : 7858939302", "700"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doc. Name : Dr. Deleep", "Hospital Address :  Bata Choak", "Experience : 5yrs", "Mob No. : 8799549564", "600"},
                    {"Doc. Name : Dr. Amit", "Hospital Address : Ahmedabaad", "Experience : 12yrs", "Mob No. : 8543535344", "900"},
                    {"Doc. Name : Dr. Sumit", "Hospital Address : Kolkata", "Experience : 3yrs", "Mob No. : 8720506821", "300"},
                    {"Doc. Name : Dr. Seema", "Hospital Address : Lucknow", "Experience : 4yrs", "Mob No. : 9489249499", "700"},
                    {"Doc. Name : Dr. Vinesh", "Hospital Address : Bhiwani", "Experience : 9yrs", "Mob No. : 7858939302", "400"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doc. Name : Dr. Ajay", "Hospital Address : Durgapur", "Experience : 5yrs", "Mob No. : 8799549564", "500"},
                    {"Doc. Name : Dr. Ashok", "Hospital Address : New Delhi", "Experience : 4yrs", "Mob No. : 8543535344", "700"},
                    {"Doc. Name : Dr. Sahil", "Hospital Address :  Mumbai", "Experience : 7yrs", "Mob No. : 8720506821", "300"},
                    {"Doc. Name : Dr. Milan", "Hospital Address : Nashik", "Experience : 9yrs", "Mob No. : 9489249499", "900"},
                    {"Doc. Name : Dr. David", "Hospital Address : Bhopal", "Experience : 10yrs", "Mob No. : 7858939302", "800"}
            };

    String[][] doctor_details = {};
    TextView intentTitle;
    Button DDback;
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter  SA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intentTitle = findViewById(R.id.textViewDDTitle);
        DDback = findViewById(R.id.buttonDDback);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        intentTitle.setText(title);

        if(title.compareTo("FAMILY PHYSICIANS") == 0){
            doctor_details = doctor_details1;
        } else if (title.compareTo("DIETICIANS") == 0){
            doctor_details = doctor_details2;
        } else if (title.compareTo("DENTISTS") == 0){
            doctor_details = doctor_details3;
        } else if (title.compareTo("SURGEONS") == 0){
            doctor_details = doctor_details4;
        } else{
            doctor_details = doctor_details5;
        }

        DDback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons. Fees: ₹"+doctor_details[i][4] + "/-");
            list.add(item);
        }

        SA = new SimpleAdapter(this,
                list,
                R.layout.multi_line,
                new String[]{"line1", "line2", "line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        ListView listView = findViewById(R.id.listViewDD);
        listView.setAdapter(SA);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);    // title is which we set intentTitle, which we previously data intent passing by findDoctor Activity.
                it.putExtra("text2",doctor_details[pos][0]);   // Doctor Name
                it.putExtra("text3",doctor_details[pos][1]);  // Hospital Address
                it.putExtra("text4",doctor_details[pos][3]); //  Mobile No.
                it.putExtra("text5",doctor_details[pos][4]);//  Cons. Fees
                startActivity(it);
            }
        });

    }
}