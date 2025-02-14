package com.example.pgarmacy;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class RegisterScreen extends AppCompatActivity {
    EditText email;
    EditText userName;
    EditText password;
    EditText rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(v -> {

            try (SqlDB sqlDB = new SqlDB(RegisterScreen.this)) {
                email = RegisterScreen.this.findViewById(R.id.email_sign_up);
                userName = RegisterScreen.this.findViewById(R.id.username_sign_up);
                password = RegisterScreen.this.findViewById(R.id.password_sign_up);
                rePassword = RegisterScreen.this.findViewById(R.id.rePassword_sign_up);


                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterScreen.this, "Enter your email.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (userName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterScreen.this, "Enter your user name.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterScreen.this, "Enter your password.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 8) {
                    Toast.makeText(RegisterScreen.this, "Your password is too short, must be more than 8.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (rePassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterScreen.this, "Please Match your password.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!rePassword.getText().toString().equals(password.getText().toString())) {
                    Toast.makeText(RegisterScreen.this, "Your password is not match.", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean ex = sqlDB.checkEmailUserExists(email.getText().toString());

                if (ex) {
                    Toast.makeText(RegisterScreen.this, "This email already exists.", Toast.LENGTH_LONG).show();
                } else {
                    sqlDB.addUser(
                            userName.getText().toString().trim(),
                            email.getText().toString().trim(),
                            password.getText().toString().trim(),
                            RegisterScreen.this);
                    Intent intent = new Intent(RegisterScreen.this, HomeScreen.class);
                    RegisterScreen.this.startActivity(intent);
                    Toast.makeText(RegisterScreen.this, "Welcome", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(RegisterScreen.this, e.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }


}