package com.example.healthcare_app.LabTestCard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.Databases.Database;
import com.example.healthcare_app.LoginAndRegister.HomeActivity;
import com.example.healthcare_app.R;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edName, edAddress, edPackagesFees, edContact;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edName = findViewById(R.id.editTextLTBFullname);
        edAddress = findViewById(R.id.editTextLTBAddress);
        edPackagesFees = findViewById(R.id.editTextLTBPincode);
        edContact = findViewById(R.id.editTextLTBContact);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote("₹"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        edPackagesFees.setKeyListener(null);
        edPackagesFees.setText(price[1]);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(),"healthcare_app", null, 1);

                db.addOrder(username, edName.getText().toString(), edAddress.getText().toString(), edPackagesFees.getText().toString(),edContact.getText().toString(),  date.toString(), time.toString(), "lab");
                db.removeCart(username,"lab");
                Toast.makeText(LabTestBookActivity.this, "Your Booking is done Successfully !", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}