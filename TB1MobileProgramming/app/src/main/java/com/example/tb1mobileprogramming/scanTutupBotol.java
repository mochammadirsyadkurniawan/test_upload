package com.example.tb1mobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class scanTutupBotol extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView cameraImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan_tutup_botol);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi ImageView untuk gambar kamera
        cameraImageView = findViewById(R.id.camera_scan);
        // Menambahkan onClickListener untuk gambar kamera
        cameraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memuat gambar dari drawable saat imageView di klik
                cameraImageView.setImageResource(R.drawable.tutup_botol);
            }
        });
    }

    public void btnSubmitTutupBotol(View view) {
        // Disini Anda dapat menyimpan gambar yang ditampilkan pada ImageView ke file
        // Misalnya, Anda dapat menggunakan FileOutputStream untuk menulis gambar ke file
        try {
            saveImageToFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show();
            return;
        }

        // Setelah menyimpan gambar, lanjutkan ke aktivitas berikutnya
        Intent intent = new Intent(this, congratulationTutupBotol.class);
        startActivity(intent);
    }

    private void saveImageToFile() throws IOException {
        // Mengambil gambar dari drawable
        // Anda dapat menggunakan metode lain jika gambar berasal dari sumber yang berbeda
        // Misalnya, jika menggunakan gambar yang diambil dari kamera, Anda dapat menggunakan
        // data bitmap yang diperoleh dari kamera.
        // Di sini saya hanya mencontohkan cara menyimpan gambar dari drawable ke file
        ImageView imageView = findViewById(R.id.camera_scan);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        // Mendapatkan gambar dari ImageView
        // Anda dapat mengganti tutup_botol dengan nama gambar yang sesuai di drawable Anda
        // Misalnya, jika gambar Anda disebut my_image, Anda harus menggunakan R.drawable.my_image
        // Di sini saya menggunakan tutup_botol untuk mengikuti contoh yang Anda berikan
        android.graphics.Bitmap bitmap = imageView.getDrawingCache();
        // Menyimpan gambar ke file
        File file = new File(getExternalFilesDir(null), "tutup_botol.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();
    }

    public void btnCancel(View view) {
        Intent intent = new Intent(this, landingActivity.class);
        startActivity(intent);
    }
}
