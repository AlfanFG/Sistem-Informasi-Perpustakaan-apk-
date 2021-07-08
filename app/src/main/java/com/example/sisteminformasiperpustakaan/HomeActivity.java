package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn1;
    CardView cv1,cv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        cv1 = (CardView) findViewById(R.id.tambahDataBuku);
        cv2 = (CardView) findViewById(R.id.tambahDataTransaksi);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,BooksActivity.class);
                startActivity(i);
                finish();
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,PinjamActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}