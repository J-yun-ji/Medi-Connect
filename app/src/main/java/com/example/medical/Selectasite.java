package com.example.medical;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Selectasite extends Activity {
    ImageButton Mainbtn, eye2, neck, stomach, waist, leg, foot, Ppage, Npage, home, community, hospital;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectasite);

        eye2 = (ImageButton) findViewById(R.id.eye);
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Eyeselectsymptom.class);
                startActivity(intent);
            }
        });

        neck = (ImageButton) findViewById(R.id.neck);
        neck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Neckselectsymptom.class);
                startActivity(intent);
            }
        });

        stomach = (ImageButton) findViewById(R.id.stomach);
        stomach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Stomachselectsymptom.class);
                startActivity(intent);
            }
        });

        waist = (ImageButton) findViewById(R.id.waist);
        waist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Waistselectsymptom.class);
                startActivity(intent);
            }
        });

        leg = (ImageButton) findViewById(R.id.leg);
        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Legselectsymptom.class);
                startActivity(intent);
            }
        });

        foot = (ImageButton) findViewById(R.id.foot);
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Footselectsymptom.class);
                startActivity(intent);
            }

        });

        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainHome.class);
                startActivity(intent);
            }
        });

        community = (ImageButton) findViewById(R.id.community);
        community.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedicalCommunity.class);
                startActivity(intent);
            }
        });

        hospital = (ImageButton) findViewById(R.id.hospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = "병원";
                String url = "kakaomap://search?q=" + keyword;

                try {
                    // 해당 url을 실행시키기 위한 intent 생성
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setPackage("net.daum.android.map");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // 카카오맵 어플이 없는 경우
                    Toast.makeText(Selectasite.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
                    // 카카오맵 어플을 다운로드 받을 수 있는 페이지 url
                    String marketUrl = "market://details?id=net.daum.android.map";
                    // 해당 url을 실행시키기 위한 intent 생성
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketUrl));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });

    }
}
