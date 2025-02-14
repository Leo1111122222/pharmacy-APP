package com.example.pgarmacy;

import android.content.Intent;
import android.os.Bundle;


import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView signUpButton = findViewById(R.id.sign_in_text_btn1);
        signUpButton.setOnClickListener(v -> {
           
            Intent intent = new Intent(MainActivity.this, RegisterScreen.class);
            MainActivity.this.startActivity(intent);
        });
        Button signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(v -> {
            try (SqlDB sqlDB = new SqlDB(MainActivity.this)) {
                email = MainActivity.this.findViewById(R.id.email_sign_in);
                password = MainActivity.this.findViewById(R.id.editTextTextPassword);
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean ex = sqlDB.userLogin(email.getText().toString().trim(), password.getText().toString().trim());
                if (ex) {
                    sqlDB.userLogin(email.getText().toString().trim(), password.getText().toString().trim());
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    MainActivity.this.startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome Back!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }


        });


    }
}