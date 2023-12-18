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

public class Footselectsymptom extends Activity {

    ImageButton chbstart, Ppage, Npage, community, home, hospital;

    ListView list;
    ArrayList<String> selectedItems = new ArrayList<>();

    String[] items = {"통증", "근육 위축", "부종", "덩어리가 만져짐", "발한", "악취", "피부염증", "발 감각 저하",
            "시림", "저림", "무감각", "말초 통증", "떨림", "엄지 발가락의 돌출", "신발 안쪽이 주로 닳음", "염좌(삠)", "굳은 살",
            "엄지 발가락 옆 뼈 통증", "보행이상", "발바닥 피부 두꺼워짐", "갈라짐", "고름", "발뒤꿈치 통증", "색깔이 퇴색해보임",
            "운동장애", "관절통", "발가락의 변형", "환부 통증", "손떨림", "사지의 창백한 현상", "허리디스크", "하지부종", "환부 냉감",
            "압통", "오한", "열", "무릎 부위 통증", "손톱이 갈라짐", "발톱이 갈라짐", "관절염"};


    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.footselectsymptom);

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
                    if ((selectedItems.contains("엄지 발가락의 돌출"))&&(selectedItems.contains("하지부종"))) {
                        intent = new Intent(Footselectsymptom.this, Halluxvalgus.class); //무지외반증
                    } else if ((selectedItems.contains("덩어리가 만져짐"))&&(selectedItems.contains("환부 통증"))) {
                        intent = new Intent(Footselectsymptom.this, Ganglion.class); // 결절종
                    }else if ((selectedItems.contains("발 발한"))&&(selectedItems.contains("악취"))&&(selectedItems.contains("피부 염증"))) {
                        intent = new Intent(Footselectsymptom.this, Localized.class); // 다한증
                    }else if ((selectedItems.contains("시림"))&&(selectedItems.contains("환부 냉감"))) {
                        intent = new Intent(Footselectsymptom.this, Coldness.class); // 수족냉증
                    }else if ((selectedItems.contains("엄지 발가락 옆 뼈 통증"))&&(selectedItems.contains("굳은 살"))) {
                        intent = new Intent(Footselectsymptom.this, Bunionette.class); // 소건막류
                    }else if ((selectedItems.contains("보행이상"))&&(selectedItems.contains("발바닥 피부 두꺼워짐"))) {
                        intent = new Intent(Footselectsymptom.this, Cavusfoot.class); // 요족
                    }else if ((selectedItems.contains("발뒤꿈치 통증"))&&(selectedItems.contains("압통"))) {
                        intent = new Intent(Footselectsymptom.this, Plantar.class); // 족저근막염
                    }else if ((selectedItems.contains("오한"))&&(selectedItems.contains("무릎 부위 통증"))&&(selectedItems.contains("열"))) {
                        intent = new Intent(Footselectsymptom.this, Gout.class); // 통풍
                    }else if ((selectedItems.contains("발가락 저림"))&&(selectedItems.contains("무감각"))) {
                        intent = new Intent(Footselectsymptom.this, Interdigital.class); // 지간 신경종
                    }else if (selectedItems.contains("발톱이 갈라짐")) {
                        intent = new Intent(Footselectsymptom.this, Tineaunguium.class); // 조갑백선
                    }else if ((selectedItems.contains("관절통"))&&(selectedItems.contains("관절염"))) {
                        intent = new Intent(Footselectsymptom.this, Goutyarthritis.class); // 통풍성 관절염
                    }else if ((selectedItems.contains("발가락의 변형"))&&(selectedItems.contains("굳은 살"))) {
                        intent = new Intent(Footselectsymptom.this, Hammertoe.class); // 망치족지
                    }
                    else {
                        intent = new Intent(Footselectsymptom.this, Ankylosing.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Footselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
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
                            Footselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
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
