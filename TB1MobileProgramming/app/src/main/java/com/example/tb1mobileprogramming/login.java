package com.example.tb1mobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.InputType;

public class login extends AppCompatActivity {

    // Define the correct email and password
    private static final String CORRECT_EMAIL = "icad@gmail.com";
    private static final String CORRECT_PASSWORD = "kyut";

    // Views
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        emailEditText = findViewById(R.id.inputEmail);
        passwordEditText = findViewById(R.id.inputPassword);
    }

    public void login(View view) {
        // Get the input email and password
        String inputEmail = emailEditText.getText().toString().trim();
        String inputPassword = passwordEditText.getText().toString().trim();

        // Check if the input email and password are correct
        if (inputEmail.equals(CORRECT_EMAIL) && inputPassword.equals(CORRECT_PASSWORD)) {
            // If correct, navigate to landingPage
            Intent intent = new Intent(login.this, landingActivity.class);
            startActivity(intent);
        } else {
            // If incorrect, show error message
            // Create a new Toast
            Toast toast = Toast.makeText(this, "Email/Password tidak valid,\nCoba lagi!", Toast.LENGTH_SHORT);
            // Set custom layout for the Toast
            View toastView = toast.getView();
            toastView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // Set text size to larger value
            TextView toastMessage = toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(18); // in SP
            // Show the Toast in the center of the screen
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    // Toggle password visibility when the pass icon is clicked
    public void togglePasswordVisibility(View view) {
        // Get the current inputType of the password EditText
        int inputType = passwordEditText.getInputType();

        // Check if the password is currently hidden
        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD || inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            // If password is currently hidden, show it
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            // If password is currently shown, hide it
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        // Move the cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.getText().length());
    }
}
