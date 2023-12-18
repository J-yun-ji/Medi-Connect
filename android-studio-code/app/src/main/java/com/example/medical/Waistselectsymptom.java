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

public class Waistselectsymptom extends Activity {

    ImageButton chbstart, Ppage, Npage, community, hospital, home;
    ListView list;

    ArrayList<String> selectedItems = new ArrayList<>();
    String[] items = {"관절통", "관절의 경직", "어깨 통증", "근육 경련", "굽은 등", "외상에 의한 척추 손상", "이완성 마비",
            "근력 약화", "감각 이상", "옆구리 통증", "사지 마비", "척추 통증", "저림", "근긴장의 이상", "요통", "체중 감소", "식욕부진",
            "보행 이상", "이완성 마비", "열", "목의 통증", "혈뇨", "구토", "호흡곤란", "배뇨 장애", "발진", "장마비", "저혈압",
            "서맥", "활력 징후 이상", "잔뇨감", "배뇨 시 통증", "하지의 근력약화", "방사통", "환부 통증", "하지 마비", "하지의 근력약화",
            "저림", "운동 장애", "근골격계 이상", "괄약근 기능 이상"};


    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.waistselectsymptom);

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
                    if ((selectedItems.contains("관절통"))&&(selectedItems.contains("관절의 경직"))&&(selectedItems.contains("어깨 통증"))) {
                        intent = new Intent(Waistselectsymptom.this, Ankylosing.class); //강식성 척추염
                    } else if ((selectedItems.contains("이완성 마비"))&&(selectedItems.contains("열"))) {
                        intent = new Intent(Waistselectsymptom.this, Polio.class); //소아마비
                    } else if ((selectedItems.contains("혈뇨"))&&(selectedItems.contains("옆구리 통증"))) {
                        intent = new Intent(Waistselectsymptom.this, Renalcell.class); //신세포암종
                    }else if ((selectedItems.contains("혈뇨"))&&(selectedItems.contains("옆구리 통증"))&&(selectedItems.contains("구토"))) {
                        intent = new Intent(Waistselectsymptom.this, Renalstone.class); //신장결석
                    }else if ((selectedItems.contains("호흡곤란"))&&(selectedItems.contains("사지 마비"))&&(selectedItems.contains("활력 징후 이상"))) {
                        intent = new Intent(Waistselectsymptom.this, Completeci.class); //완전 척수 손상
                    }else if ((selectedItems.contains("잔뇨감"))&&(selectedItems.contains("배뇨 시 통증"))) {
                        intent = new Intent(Waistselectsymptom.this, Urinaryti.class); //요로감염
                    }else if ((selectedItems.contains("배뇨 장애"))&&(selectedItems.contains("발진"))&&(selectedItems.contains("장마비"))) {
                        intent = new Intent(Waistselectsymptom.this, Traumaticsci.class); //외상에 의한 척수 손상
                    }else if ((selectedItems.contains("척추 통증"))&&(selectedItems.contains("활력 징후 이상"))) {
                        intent = new Intent(Waistselectsymptom.this, Traumaticsi.class); //외상에 의한 척추 손상
                    }else if ((selectedItems.contains("척추 통증"))&&(selectedItems.contains("환부 통증"))&&(selectedItems.contains("저림"))) {
                        intent = new Intent(Waistselectsymptom.this, Lowbackpain.class); //요통
                    }else if ((selectedItems.contains("감각 이상"))&&(selectedItems.contains("요통"))&&(selectedItems.contains("환부 통증"))) {
                        intent = new Intent(Waistselectsymptom.this, Spinalct.class); //척수 종양
                    }else if ((selectedItems.contains("보행 이상"))&&(selectedItems.contains("근긴장의 이상"))) {
                        intent = new Intent(Waistselectsymptom.this, Spinalma.class); //척수 근육 위축
                    }else if ((selectedItems.contains("굽은 등"))&&(selectedItems.contains("척추 통증"))&&(selectedItems.contains("근골격계 이상"))) {
                        intent = new Intent(Waistselectsymptom.this, Scoliosis.class); //척추측만증
                    }
                    else {
                        intent = new Intent(Waistselectsymptom.this, Ankylosing.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Waistselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
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
                            Waistselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
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
