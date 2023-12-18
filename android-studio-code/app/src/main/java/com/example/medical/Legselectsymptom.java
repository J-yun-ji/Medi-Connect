package com.example.medical;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Legselectsymptom extends Activity {

    ImageButton chbstart, Ppage, Npage, community, home, hospital;

    ListView list;
    ArrayList<String> selectedItems = new ArrayList<>();

    String[] items = {"근육 위축", "근력 약화", "발끝으로 설 수 없음", "발 굴곡 운동의 제한", "관절 운동성 감소", "통증",
            "무릎 부위 부종", "뼈의 통증", "뻣뻣함", "발적", "O자 다리", "하지의 근력약화", "보행 이상", "다리의 길이가 다름", "압통",
            "다리 변형", "관절통", "사지 부종", "관절 잡음", "파열음", "파열 부위 오목해짐", "발목 통증", "골반 통증", "저림",
            "하지 부종", "경련", "피로감", "수면 장애", "체중감소", "무감각", "환부 통증", "덩어리가 만져짐", "창백", "손상 부위 통증",
            "열감", "피부 긴장도 저하"};


    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.legselectsymptom);

        list = findViewById(R.id.symptomchoice);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, items);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = (String) adapterView.getItemAtPosition(position);
                if (list.isItemChecked(position)){
                    selectedItems.add(selectedItem);
                }else{
                    selectedItems.remove(selectedItem);
                }
            }
        });

        Ppage = (ImageButton) findViewById(R.id.Ppage);
        Ppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Selectasite.class);
                startActivity(intent);
            }
        });

        Npage = (ImageButton) findViewById(R.id.Npage);
        Npage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItems.size() > 1) {
                    Intent intent;
                    if ((selectedItems.contains("근육 위축"))&&(selectedItems.contains("근력 약화"))&&(selectedItems.contains("체중감소"))) {
                        intent = new Intent(Legselectsymptom.this, Beriberi.class); //각기병
                    } else if ((selectedItems.contains("관절 운동성 감소"))&&(selectedItems.contains("무릎 부위 통증"))) {
                        intent = new Intent(Legselectsymptom.this, Osteoarthritis.class); // 골관절염
                    }else if ((selectedItems.contains("뻣뻣함"))&&(selectedItems.contains("발적"))&&(selectedItems.contains("환부 부종"))) {
                        intent = new Intent(Legselectsymptom.this, Arthritis.class); // 관절염
                    }else if ((selectedItems.contains("다리 변형"))&&(selectedItems.contains("손상 부위 통증"))) {
                        intent = new Intent(Legselectsymptom.this, Fracture.class); // 대퇴 골절
                    }else if ((selectedItems.contains("손마디가 뻣뻣해짐"))&&(selectedItems.contains("열감"))&&(selectedItems.contains("관절통"))) {
                        intent = new Intent(Legselectsymptom.this, Rheumatoid.class); // 류마티스 관절염
                    }else if ((selectedItems.contains("무릎 부위 부종"))&&(selectedItems.contains("관절 잡음"))) {
                        intent = new Intent(Legselectsymptom.this, Gonarthrosis.class); // 무릎관절증
                    }else if ((selectedItems.contains("관절 잡음"))&&(selectedItems.contains("무릎 부위 부종"))&&(selectedItems.contains("환부 부종"))) {
                        intent = new Intent(Legselectsymptom.this, Cruciateli.class); // 십자인대 손상
                    }else if ((selectedItems.contains("파열 부위 오목해짐"))&&(selectedItems.contains("발끝으로 설 수 없음"))) {
                        intent = new Intent(Legselectsymptom.this, Acuteachilles.class); // 아킬레스 건 파열
                    }else if ((selectedItems.contains("관절 운동성 감소"))&&(selectedItems.contains("관절통"))) {
                        intent = new Intent(Legselectsymptom.this, Chondromalacia.class); // 연골연화증
                    }else if ((selectedItems.contains("발목 통증"))&&(selectedItems.contains("관절통"))) {
                        intent = new Intent(Legselectsymptom.this, Sprain.class); // 염좌
                    }else if ((selectedItems.contains("하지 부종"))&&(selectedItems.contains("경련"))) {
                        intent = new Intent(Legselectsymptom.this, Varicoseveins.class); // 하지정맥류
                    }else if ((selectedItems.contains("저림"))&&(selectedItems.contains("통증"))&&(selectedItems.contains("수면 장애"))) {
                        intent = new Intent(Legselectsymptom.this, Restless.class); // 하지불안증후군
                    }
                    else {
                        intent = new Intent(Legselectsymptom.this, Sprain.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Legselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
                }
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
                    Toast.makeText(
                            Legselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
                    // 카카오맵 어플을 다운로드 받을 수 있는 페이지 url
                    String marketUrl = "market://details?id=net.daum.android.map";
                    // 해당 url을 실행시키기 위한 intent 생성
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketUrl));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });

    };

}
