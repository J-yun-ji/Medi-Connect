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

public class Eyeselectsymptom extends Activity {
    ListView list;
    ImageButton Ppage, Npage, home, community, hospital, mainbtn, chbstart;

    ArrayList<String> selectedItems = new ArrayList<>();

    String[] items = {"눈의 충혈", "눈물", "시야장애", "눈꼽", "눈의 피로", "주변 시야 손상", "눈꺼풀 처짐",
            "환부의 분비물", "사물이 찌그러져 보인다", "시력 감소", "야맹", "안구혼탁", "눈을 감을때 번쩍거린다",
            "홍채가 흔들거린다", "앞이 뿌옇게 보인다", "안구 건조", "눈 주변 부종", "당뇨병", "광시증","눈의 자극감",
            "고름", "두통", "호홉곤란", "재채기", "근시", "두통", "눈부심", "어깨 통증", "굽은 등", "부유물이 보임"};


    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.eyeselectsymptom);

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



        Ppage =  findViewById(R.id.Ppage);
        Ppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Selectasite.class);
                startActivity(intent);
            }
        });

        Npage =  findViewById(R.id.Npage);
        Npage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItems.size() > 1) {
                    Intent intent;
                    if ((selectedItems.contains("눈의 충혈"))&&(selectedItems.contains("어깨 통증"))&&(selectedItems.contains("굽은 등"))) {
                        intent = new Intent(Eyeselectsymptom.this, Vdt.class); //vdt 증후군
                    } else if ((selectedItems.contains("눈의 충혈"))&&(selectedItems.contains("눈물"))) {
                        intent = new Intent(Eyeselectsymptom.this, Keratitis.class); //각막염
                    } else if (selectedItems.contains("눈의 충혈")&&(selectedItems.contains("눈꼽"))) {
                        intent = new Intent(Eyeselectsymptom.this, Conjunctivitis.class); //결막염
                    }else if (selectedItems.contains("눈의 충혈")&&(selectedItems.contains("눈꼽"))&&(selectedItems.contains("호흡곤란"))) {
                        intent = new Intent(Eyeselectsymptom.this, Pollen.class); //꽃가루 알레르기
                    }else if (selectedItems.contains("눈의 충혈")&&(selectedItems.contains("눈물"))&&(selectedItems.contains("시력 감소"))) {
                        intent = new Intent(Eyeselectsymptom.this, Corneal.class); //각막 궤양
                    }else if (selectedItems.contains("시야장애")&&(selectedItems.contains("근시"))) {
                        intent = new Intent(Eyeselectsymptom.this, Cedema.class); //각막 부종
                    }else if (selectedItems.contains("눈물")&&(selectedItems.contains("환부의 분비물"))) {
                        intent = new Intent(Eyeselectsymptom.this, Epiphora.class); //눈물 흘림증
                    }else if (selectedItems.contains("주변 시야 손상")&&(selectedItems.contains("시력 감소"))) {
                        intent = new Intent(Eyeselectsymptom.this, Glaucoma.class); //녹내장
                    } else if (selectedItems.contains("시력 감소")&&(selectedItems.contains("야맹"))) {
                        intent = new Intent(Eyeselectsymptom.this, Retinitis.class); //망막색소변성증
                    }else if (selectedItems.contains("시력 감소")&&(selectedItems.contains("안구혼탁"))) {
                        intent = new Intent(Eyeselectsymptom.this, Cataract.class); //백내장
                    }else if (selectedItems.contains("부유물이 보임")&&(selectedItems.contains("눈을 감을때 번쩍거린다"))) {
                        intent = new Intent(Eyeselectsymptom.this, Floaters.class); //비문증
                    }else if (selectedItems.contains("안구 건조")&&(selectedItems.contains("눈의 자극감"))) {
                        intent = new Intent(Eyeselectsymptom.this, Floaters.class); //안구 건조증
                    }
                    else {
                        intent = new Intent(Eyeselectsymptom.this, Keratitis.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Eyeselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        home =  findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainHome.class);
                startActivity(intent);
            }
        });

        community =  findViewById(R.id.community);
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
                            Eyeselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
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
