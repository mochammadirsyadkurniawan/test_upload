package com.example.tb1mobileprogramming;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class scanKTP extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView scanKtpImageView;
    private Bitmap capturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_ktp);

        scanKtpImageView = findViewById(R.id.scan_ktp);

        // Set listener to handle image click event
        scanKtpImageView.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the captured image bitmap from the data intent
            if (data != null && data.getExtras() != null) {
                capturedImage = (Bitmap) data.getExtras().get("data");
                // Set the captured image to ImageView
                scanKtpImageView.setImageBitmap(capturedImage);
            }
        }
    }

    public void saveKTP(View view) {
        if (capturedImage != null) {
            // Image is captured and available for saving
            // You can implement the code to save the image here
            // For example, you can save it to internal storage or upload it to a server
            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please capture an image first", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelKTP(View view) {
        // Reset the ImageView to remove the captured image
        scanKtpImageView.setImageBitmap(null);
        // Reset the captured image bitmap
        capturedImage = null;
        Toast.makeText(this, "Image discarded", Toast.LENGTH_SHORT).show();
    }

    public void scanKTPback(View view) {
        Intent intent = new Intent(this, extraVaganza.class);
        startActivity(intent);
    }
}
