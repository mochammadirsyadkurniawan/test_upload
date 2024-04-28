package com.example.tb1mobileprogramming;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;



public class landingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.logout) {
                // Buka halaman pertama jika item menu 1 diklik
                Intent intent1 = new Intent(landingActivity.this, home.class);
                startActivity(intent1);
            }
            drawerLayout.closeDrawers();
            return true;
        });

    }


    // Menampilkan menu navigasi
    public void tampilkanMenu(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }



    // pindah activity ke redeemVaganza
    public void onViewTravelokaClicked(View view) {
        Intent intent = new Intent(this, reedemVaganza.class);
        startActivity(intent);

    }

    // method untuk menangani klik pada view7
    public void onView7Clicked(View view) {
        // Buat Intent untuk memulai aktivitas scan tutup botol.xml
        Intent intent = new Intent(this, scanTutupBotol.class);
        startActivity(intent);
    }


    // pindah activity ke undian extra vaganza
    public void extraVaganzaUndian(View view) {
        Intent intent = new Intent(this, congratulationExtraVaganza.class);
        startActivity(intent);
    }

}
