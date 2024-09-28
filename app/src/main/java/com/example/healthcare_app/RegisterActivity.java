package com.example.healthcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirm;
    Button register;
    TextView existUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUsername = findViewById(R.id.editTextRegUserName);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        register = findViewById(R.id.buttonRegister);
        existUser = findViewById(R.id.textViewExistingUser);

        existUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare_app", null, 1);

                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please fill All Details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm) == 0){
                        if(isValid(password)){
                            db.register(username, email, password);   /// here, we added new user.
                            Toast.makeText(RegisterActivity.this, "New User Recorded !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Password must contains atleast 8 characters, having first char capital letter, having digit, having symbol", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password & Confirm Password didn't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String password){
        int f1 = 0, f2 = 0, f3 = 0;
        if(password.length() < 8){
            return false;
        }else{
            for(int p = 0; p < password.length(); p++){
                if(Character.isLetter(password.charAt(p)) && Character.isUpperCase(password.charAt(0))){
                    f1 = 1;
                }
            }
            for(int q = 0; q < password.length(); q++){
                if(Character.isDigit(password.charAt(q))){
                    f2 = 1;
                }
            }
            for(int r = 0; r < password.length(); r++){
                char c = password.charAt(r);
                if(c >= 33 && c <= 46 || c == 64){
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}