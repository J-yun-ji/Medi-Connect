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

public class Neckselectsymptom extends Activity {
    ImageButton chbstart, home, community, hospital, Npage, Ppage;
    ListView list;
    ArrayList<String> selectedItems = new ArrayList<>();

    String[] items = {"목의 통증", "호흡곤란", "갑상선이 단단해짐", "목소리 변화", "부정맥", "통증 없는 부종",
            "관절통", "덩어리가 만져짐", "목의 이물감", "기침", "가래", "고음에서의 분열", "재채기", "목 주변 부종","갑상선의 비대",
            "노란 이물질", "구취", "목소리 변화", "월경과다", "오한", "식욕부진", "설사", "무월경", "안구돌출", "불면증",
            "신경과민", "어깨 통증", "어지러움", "눈의 피로", "열", "압통", "발진", "체중 감소", "발작", "가슴 통증", "콧물",
            "이중음성", "음성 피로", "구토", "삼키기 곤란", "소화불량", "후각장애", "코막힘", "척추통증", "활력징후 이상", "저혈압",
            "귀의 통증", "보행이상", "근력 약화", "편도선 비대"};


    public void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.neckselectsymptom);

        list = (ListView) findViewById(R.id.symptomchoice);

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
                    if ((selectedItems.contains("갑상선이 단단해짐"))&&(selectedItems.contains("호흡곤란"))) {
                        intent = new Intent(Neckselectsymptom.this, Thyroidnodule.class); //갑상선 결절
                    }else if ((selectedItems.contains("목소리 변화"))&&(selectedItems.contains("월경과다"))&&(selectedItems.contains("식욕부진"))) {
                        intent = new Intent(Neckselectsymptom.this, Hypothyroidism.class); //갑상선기능저하증
                    }else if ((selectedItems.contains("갑상선이 단단해짐"))&&(selectedItems.contains("갑상선의 비대"))) {
                        intent = new Intent(Neckselectsymptom.this, Thyroiditis.class); //갑상선염
                    }else if ((selectedItems.contains("목의 통증"))&&(selectedItems.contains("어깨 통증"))&&(selectedItems.contains("눈의 피로"))) {
                        intent = new Intent(Neckselectsymptom.this, Turtle.class); //거북목 증후군
                    }else if ((selectedItems.contains("기침"))&&(selectedItems.contains("코막힘"))&&(selectedItems.contains("콧물"))) {
                        intent = new Intent(Neckselectsymptom.this, Upper.class); //상기도 감염
                    }else if ((selectedItems.contains("목의 통증"))&&(selectedItems.contains("고음에서의 분열"))) {
                        intent = new Intent(Neckselectsymptom.this, Nodulesvocal.class); //성대 결절
                    }else if ((selectedItems.contains("콧물"))&&(selectedItems.contains("재채기"))&&(selectedItems.contains("코막힘"))) {
                        intent = new Intent(Neckselectsymptom.this, Allergicrhinitis.class); //알레르기 비염
                    }else if ((selectedItems.contains("척추통증"))&&(selectedItems.contains("활력징후 이상"))) {
                        intent = new Intent(Neckselectsymptom.this, Traumaticsi.class); //외상에 의한 척추 손상
                    }else if ((selectedItems.contains("열"))&&(selectedItems.contains("목 주변 부종"))) {
                        intent = new Intent(Neckselectsymptom.this, Mumps.class); //유행성 이하선염
                    }else if ((selectedItems.contains("삼키기 곤란"))&&(selectedItems.contains("목의 이물감"))) {
                        intent = new Intent(Neckselectsymptom.this, Laryngopharyngitis.class); //인후두염
                    }else if ((selectedItems.contains("삼키기 곤란"))&&(selectedItems.contains("편도선 비대"))) {
                        intent = new Intent(Neckselectsymptom.this, Tonsillitis.class); //편도선염
                    }else if ((selectedItems.contains("노란 이물질"))&&(selectedItems.contains("구취"))) {
                        intent = new Intent(Neckselectsymptom.this, Tonsillolith.class); //편도결석
                    }
                    else {
                        intent = new Intent(Neckselectsymptom.this, Upper.class);
                    }
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Neckselectsymptom.this, "2개 이상 선택해주세요!",Toast.LENGTH_SHORT).show();
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
                            Neckselectsymptom.this, "카카오맵 어플을 설치해주세요.", Toast.LENGTH_SHORT).show();
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
