package com.example.medical;

import static com.example.medical.PostRegistration.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MedicalCommunity extends Activity {
    public ListView listView;
    static DatabaseReference databaseReference;
    static PostAdapter postAdapter;

    public TextView commentCountTextView;

    // 버튼 기능들
    ImageButton qnaBtn, issueBtn, freeBtn, notBtn, mypBtn, postBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicalcommunity);

        // post_item.xml 파일의 뷰를 인플레이트하여 참조 얻어오기
        View postItemView = getLayoutInflater().inflate(R.layout.post_item, null);
        TextView commentCountTextView = postItemView.findViewById(R.id.comment_count_text_view);

        listView = findViewById(R.id.list_view);
        // Firebase Database에서 데이터를 가져와서 postList에 저장.
        List<Post> postList = new ArrayList<>();
        postAdapter = new PostAdapter(this, postList);
        // 게시글 리스트 출력
        listView.setAdapter(postAdapter);
        // 게시글 리스트 클릭
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭된 게시글의 ID를 가져옴
                String postId = postList.get(position).getId();
                String title = postList.get(position).getTitle();
                String content = postList.get(position).getContent();
                long timestamp = postList.get(position).getTimestamp();
                int commentCount = postList.get(position).getCommentCount();


                // 해당 게시글의 내용을 보여주는 액티비티로 전환
                Intent intent = new Intent(MedicalCommunity.this, DetailActivity.class);
                intent.putExtra("postId", postId);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("timestamp", timestamp);
                intent.putExtra("commentCount", commentCount);
                startActivity(intent);
            }
        });


        // 스피너
        if (category == null) {
            category = "정보이슈";
        }
        else if (category.equals("Q&A")) {
            // Q&A 게시판 버튼 이미지 변경
            ImageButton Qna = findViewById(R.id.Qna);
            Qna.setImageResource(R.drawable.qnaclick);
            // 정보이슈 게시판 버튼 이미지 원상복구.
            ImageButton Issue = findViewById(R.id.Issue);
            Issue.setImageResource(R.drawable.informationissue);
            // 자유 게시판 버튼 이미지 원상복구.
            ImageButton Free = findViewById(R.id.Free);
            Free.setImageResource(R.drawable.free);
            // 공지사항 게시판 버튼 이미지 원상복구.
            ImageButton Not = findViewById(R.id.Not);
            Not.setImageResource(R.drawable.notice);
        } else if (category.equals("정보이슈")) {
            // Q&A 게시판 버튼 이미지 원상복구.
            ImageButton Qna = findViewById(R.id.Qna);
            Qna.setImageResource(R.drawable.qna);
            // 정보이슈 게시판 버튼 이미지 변경
            ImageButton Issue = findViewById(R.id.Issue);
            Issue.setImageResource(R.drawable.informationissueclick);
            // 자유 게시판 버튼 이미지 원상복구.
            ImageButton Free = findViewById(R.id.Free);
            Free.setImageResource(R.drawable.free);
            // 공지사항 게시판 버튼 이미지 원상복구.
            ImageButton Not = findViewById(R.id.Not);
            Not.setImageResource(R.drawable.notice);
        } else if (category.equals("자유")) {
            // Q&A 게시판 버튼 이미지 원상복구.
            ImageButton Qna = findViewById(R.id.Qna);
            Qna.setImageResource(R.drawable.qna);
            // 정보이슈 게시판 버튼 이미지 원상복구.
            ImageButton Issue = findViewById(R.id.Issue);
            Issue.setImageResource(R.drawable.informationissue);
            // 자유 게시판 버튼 이미지 변경
            ImageButton Free = findViewById(R.id.Free);
            Free.setImageResource(R.drawable.freeclick);
            // 공지사항 게시판 버튼 이미지 원상복구.
            ImageButton Not = findViewById(R.id.Not);
            Not.setImageResource(R.drawable.notice);
        } else if (category.equals("공지사항")) {
            // Q&A 게시판 버튼 이미지 원상복구.
            ImageButton Qna = findViewById(R.id.Qna);
            Qna.setImageResource(R.drawable.qna);
            // 정보이슈 게시판 버튼 이미지 원상복구.
            ImageButton Issue = findViewById(R.id.Issue);
            Issue.setImageResource(R.drawable.informationissue);
            // 자유 게시판 버튼 이미지 원상복구.
            ImageButton Free = findViewById(R.id.Free);
            Free.setImageResource(R.drawable.free);
            // 공지사항 게시판 버튼 이미지 변경
            ImageButton Not = findViewById(R.id.Not);
            Not.setImageResource(R.drawable.noticeclick);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("posts/정보이슈");
        // TODO: Implement reading posts from the database and displaying them in the ListView
        // DB에서 게시글 읽기
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postAdapter.clear();
                // 전체 댓글 개수를 저장할 변수 초기화
                int totalCommentCount = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);

                    // 게시글의 댓글 개수 가져오기

                    Integer commentCountValue = postSnapshot.child("commentsCount").getValue(Integer.class);
                    int commentCount = (commentCountValue != null) ? commentCountValue.intValue() : 0;
                    totalCommentCount += commentCount;
                    post.setCommentCount(commentCount);


                    // 해당 게시글의 좋아요 개수 가져오기
                    int likeCount = (int) postSnapshot.child("likes").getChildrenCount();

                    // 해당 게시글의 댓글 개수와 좋아요 개수를 post 객체에 저장
                    //post.setCommentCount(commentCount);
                   // post.setLikeCount(likeCount);

                    postAdapter.add(post);
                }
                // 전체 댓글 개수를 commentCountTextView에 표시
                //commentCountTextView.setText(String.valueOf(totalCommentCount));
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database read error
            }
        });

        qnaBtn = findViewById(R.id.Qna);
        qnaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Q&A 게시판 버튼 이미지 변경
                ImageButton Qna = findViewById(R.id.Qna);
                Qna.setImageResource(R.drawable.qnaclick);
                // 정보이슈 게시판 버튼 이미지 원상복구.
                ImageButton Issue = findViewById(R.id.Issue);
                Issue.setImageResource(R.drawable.informationissue);
                // 자유 게시판 버튼 이미지 원상복구.
                ImageButton Free = findViewById(R.id.Free);
                Free.setImageResource(R.drawable.free);
                // 공지사항 게시판 버튼 이미지 원상복구.
                ImageButton Not = findViewById(R.id.Not);
                Not.setImageResource(R.drawable.notice);
                databaseReference = FirebaseDatabase.getInstance().getReference("posts/Q&A");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postAdapter.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Post post = postSnapshot.getValue(Post.class);
                            postAdapter.add(post);
                        }
                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database read error
                    }
                });
            }
        });

        issueBtn = findViewById(R.id.Issue);
        issueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Q&A 게시판 버튼 이미지 원상복구.
                ImageButton Qna = findViewById(R.id.Qna);
                Qna.setImageResource(R.drawable.qna);
                // 정보이슈 게시판 버튼 이미지 변경
                ImageButton Issue = findViewById(R.id.Issue);
                Issue.setImageResource(R.drawable.informationissueclick);
                // 자유 게시판 버튼 이미지 원상복구.
                ImageButton Free = findViewById(R.id.Free);
                Free.setImageResource(R.drawable.free);
                // 공지사항 게시판 버튼 이미지 원상복구.
                ImageButton Not = findViewById(R.id.Not);
                Not.setImageResource(R.drawable.notice);
                databaseReference = FirebaseDatabase.getInstance().getReference("posts/정보이슈");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postAdapter.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Post post = postSnapshot.getValue(Post.class);
                            postAdapter.add(post);
                        }
                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database read error
                    }
                });
            }
        });

        freeBtn = findViewById(R.id.Free);
        freeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Q&A 게시판 버튼 이미지 원상복구.
                ImageButton Qna = findViewById(R.id.Qna);
                Qna.setImageResource(R.drawable.qna);
                // 정보이슈 게시판 버튼 이미지 원상복구.
                ImageButton Issue = findViewById(R.id.Issue);
                Issue.setImageResource(R.drawable.informationissue);
                // 자유 게시판 버튼 이미지 변경
                ImageButton Free = findViewById(R.id.Free);
                Free.setImageResource(R.drawable.freeclick);
                // 공지사항 게시판 버튼 이미지 원상복구.
                ImageButton Not = findViewById(R.id.Not);
                Not.setImageResource(R.drawable.notice);
                databaseReference = FirebaseDatabase.getInstance().getReference("posts/자유");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postAdapter.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Post post = postSnapshot.getValue(Post.class);
                            postAdapter.add(post);
                        }
                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database read error
                    }
                });
            }
        });

        notBtn = findViewById(R.id.Not);
        notBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Q&A 게시판 버튼 이미지 원상복구.
                ImageButton Qna = findViewById(R.id.Qna);
                Qna.setImageResource(R.drawable.qna);
                // 정보이슈 게시판 버튼 이미지 원상복구.
                ImageButton Issue = findViewById(R.id.Issue);
                Issue.setImageResource(R.drawable.informationissue);
                // 자유 게시판 버튼 이미지 원상복구.
                ImageButton Free = findViewById(R.id.Free);
                Free.setImageResource(R.drawable.free);
                // 공지사항 게시판 버튼 이미지 변경
                ImageButton Not = findViewById(R.id.Not);
                Not.setImageResource(R.drawable.noticeclick);
                databaseReference = FirebaseDatabase.getInstance().getReference("posts/공지사항");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postAdapter.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Post post = postSnapshot.getValue(Post.class);
                            postAdapter.add(post);
                        }
                        postAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database read error
                    }
                });
            }
        });

        postBtn = findViewById(R.id.post);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PostRegistration.class);
                startActivity(intent);
                // Q&A 게시판 버튼 이미지 원상복구.
                ImageButton Qna = findViewById(R.id.Qna);
                Qna.setImageResource(R.drawable.qna);
                // 정보이슈 게시판 버튼 이미지 원상복구.
                ImageButton Issue = findViewById(R.id.Issue);
                Issue.setImageResource(R.drawable.informationissue);
                // 자유 게시판 버튼 이미지 원상복구.
                ImageButton Free = findViewById(R.id.Free);
                Free.setImageResource(R.drawable.freeclick);
                // 공지사항 게시판 버튼 이미지 원상복구.
                ImageButton Not = findViewById(R.id.Not);
                Not.setImageResource(R.drawable.notice);


            }
        });
        homeBtn = (ImageButton) findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainHome.class);
                startActivity(intent);
            }
        });

        mypBtn = (ImageButton) findViewById(R.id.myp);
        mypBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });
    }
}