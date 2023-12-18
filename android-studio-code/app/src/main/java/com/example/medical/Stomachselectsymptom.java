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

public class Stomachselectsymptom extends Activity {

    String[] items = {"통증", "복부 불편감", "식욕부진", "구토", "복부팽만감", "복수", "설사", "혈변", "무월경",
            "희발 월경", "비정상적 질출혈", "점액변", "옆구리 통증", "변비", "덩어리가 만져짐", "식후 불쾌감", "트림", "포만감",
            "소화불량", "헛구역질", "옆구리 통증", "황달", "피로감", "인지장애", "불면증", "자세 불안정", "감정 변화", "두통",
            "관절통", "진한 소변색", "열", "빈호흡", "골반 통증", "악취", "소변량 감소", "쇼크", "전신 부종", "배뇨 곤란",
            "배뇨 시 통증", "잔뇨감", "여드름", "하지부종", "창백", "저혈압", "저체온", "두드러기", "운동 시 호흡곤란",
            "고혈압", "가슴 쓰림", "혈뇨", "헛구역질", "희발 월경", "어지러움"};

    ImageButton chbstart, Ppage, Npage, home, hospital, community;

    ListView  list;

    ArrayList<String> selectedItems = new ArrayList<>();


    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.stomachselectsymptom);

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
                    if ((selectedItems.contains("황달"))&&(selectedItems.contains("진한 소변색"))&&(selectedItems.contains("구토"))) {
                        intent = new Intent(Stomachselectsymptom.this, Heapatitis.class); //간염
                    } else if ((selectedItems.contains("설사"))&&(selectedItems.contains("복부팽만감"))) {
                        intent = new Intent(Stomachselectsymptom.this, Gastroenteritis.class); //급성 위장염
                    }else if ((selectedItems.contains("무월경"))&&(selectedItems.contains("비정상적 질출혈"))) {
                        intent = new Intent(Stomachselectsymptom.this, Polycystic.class); //다낭성 난소 증후군
                    }
                    else if ((selectedItems.contains("희발 월경"))&&(selectedItems.contains("비정상적 질출혈"))) {
                        intent = new Intent(Stomachselectsymptom.this, Polycystic.class); //다낭성 난소 증후군
                    }else if ((selectedItems.contains("점액변"))&&(selectedItems.contains("설사"))) {
                        intent = new Intent(Stomachselectsymptom.this, Colitis.class); //대장염
                    }else if ((selectedItems.contains("복부 심한 통증"))&&(selectedItems.contains("설사"))&&(selectedItems.contains("변비"))) {
                        intent = new Intent(Stomachselectsymptom.this, Appendicitis.class); //맹장염
                    }else if ((selectedItems.contains("어지러움"))&&(selectedItems.contains("창백"))&&(selectedItems.contains("저혈압"))) {
                        intent = new Intent(Stomachselectsymptom.this, Bleeding.class); //복강 내 출혈
                    }else if ((selectedItems.contains("구토"))&&(selectedItems.contains("트림"))&&(selectedItems.contains("포만감"))&&(selectedItems.contains("식후 불쾌감"))) {
                        intent = new Intent(Stomachselectsymptom.this, Dyspepsia.class); //소화불량
                    }else if ((selectedItems.contains("설사"))&&(selectedItems.contains("구토")&&(selectedItems.contains("두드러기")))) {
                        intent = new Intent(Stomachselectsymptom.this, Foodborne.class); //식중독
                    }else if ((selectedItems.contains("운동 시 호흡곤란"))&&(selectedItems.contains("소변량 감소"))&&(selectedItems.contains("고혈압"))) {
                        intent = new Intent(Stomachselectsymptom.this, Renalartery.class); //신동맥 협착증
                    }else if ((selectedItems.contains("소화불량"))&&(selectedItems.contains("가슴 쓰림"))&&(selectedItems.contains("복부팽만감"))) {
                        intent = new Intent(Stomachselectsymptom.this, Duodenitis.class); //십이지장염
                    }else if ((selectedItems.contains("복부팽만감"))&&(selectedItems.contains("혈뇨"))&&(selectedItems.contains("옆구리 통증"))) {
                        intent = new Intent(Stomachselectsymptom.this, Urolithiasis.class); //요로결석
                    }else if ((selectedItems.contains("구토"))&&(selectedItems.contains("헛구역질"))) {
                        intent = new Intent(Stomachselectsymptom.this, Stomachcramp.class); //위경련
                    }
                    else {
                        intent = new Intent(Stomachselectsymptom.this, Gastroenteritis.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Stomachselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
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
                            Stomachselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
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
