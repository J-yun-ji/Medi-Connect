package com.example.medical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostRegistration extends Activity {

    public EditText writeEdit1, writeEdit2;
    public Button readBtn;
    ImageButton mypBtn, postBtn, homeBtn;
    static DatabaseReference databaseReference;
    static String category;
    static PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writepost);

        String[] bulletinBoard = { "카테고리를 선택하세요.", "Q&A", "정보이슈", "자유", "공지사항" };

        Spinner spinner = findViewById(R.id.category);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bulletinBoard);
        spinner.setAdapter(adapter);

        writeEdit1 = findViewById(R.id.title_et);
        writeEdit2 = findViewById(R.id.content_et);
        readBtn = findViewById(R.id.reg_button);

        // Firebase Database의 "posts" 참조를 가져옴.
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");

        // Firebase Database에서 데이터를 가져와서 postList에 저장.
        List<Post> postList = new ArrayList<>();
        // postAdapter 생성.
        postAdapter = new PostAdapter(this, postList);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = writeEdit1.getText().toString();
                String content = writeEdit2.getText().toString();
                category = spinner.getSelectedItem().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    if (title.isEmpty() && content.isEmpty()) {
                        Toast.makeText(PostRegistration.this, "제목과 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if (title.isEmpty()) {
                        Toast.makeText(PostRegistration.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if (content.isEmpty()) {
                        Toast.makeText(PostRegistration.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else if (category.equals("카테고리를 선택하세요.")) {
                    Toast.makeText(PostRegistration.this, "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 현재 날짜와 시간을 Timestamp로 변환
                    long timestamp = new Date().getTime();
                    int commentCount = 0;
                    int likeCount = 0;

                    // 게시글 고유 키 생성
                    //String postId = databaseReference.push().getKey();
                    String postId = databaseReference.child(category).push().getKey();
                    // 게시글 객체 생성
                    Post post = new Post(postId, title, content, timestamp, commentCount, likeCount);
                    // 현재 날짜를 게시글 객체에 설정
                    post.setTimestamp(timestamp);
                    // 게시글 DB 저장
                    //databaseReference.child(postId).setValue(post);
                    databaseReference.child(category).child(postId).setValue(post);
                    // 입력 필드 리셋
                    writeEdit1.setText("");
                    writeEdit2.setText("");

                    //등록 후 선택한 카테고리의 게시판으로 이동.

                    if (category.equals("Q&A")) {
                        // Q&A 게시판으로 이동하는 로직 추가
                        //Intent intent = new Intent(PostRegistration.this, 큐엔에이게시판.class);
                        //startActivity(intent);
                    } else if (category.equals("정보이슈")) {
                        // 정보이슈 게시판으로 이동하는 로직 추가
                        //Intent intent = new Intent(PostRegistration.this, 정보이슈게시판.class);
                        //startActivity(intent);
                    } else if (category.equals("자유")) {
                        // 자유 게시판으로 이동하는 로직 추가
                        //Intent intent = new Intent(PostRegistration.this, 자유게시판.class);
                        //startActivity(intent);
                    } else if (category.equals("공지사항")) {
                        // 공지사항 게시판으로 이동하는 로직 추가
                        //Intent intent = new Intent(PostRegistration.this, 공지사항.class);
                        //startActivity(intent);
                    }
                    Toast.makeText(PostRegistration.this, "게시글이 발행되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MedicalCommunity.class);
                    startActivity(intent);
                }
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
class Post {
    private String id;
    private String title;
    private String content;
    private long timestamp; // 날짜를 저장할 timestamp 필드
    private int commentCount;
    private int likeCount;


    public Post() {
        // DataSnapshot.getValue(Post.class) 호출에 필요한 기본 생성자.
    }

    public Post(String id, String title, String content, long timestamp, int commentCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public String getId() { return id; }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public int getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}

class PostAdapter extends ArrayAdapter<Post> {
    public Context context;
    public List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        super(context, 0, postList);
        this.context = context;
        this.postList = postList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 리스트 아이템을 표시할 View를 생성하거나 재사용.
        // 데이터를 가져와서 View에 바인딩하는 로직을 작성.
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        }

        Post currentPost = postList.get(position);

        TextView titleTextView = itemView.findViewById(R.id.title_text_view);
        TextView contentTextView = itemView.findViewById(R.id.content_text_view);
        TextView timestampTextView = itemView.findViewById(R.id.timestamp_text_view);
        TextView commentCountTextView = itemView.findViewById(R.id.comment_count_text_view);
        TextView likeCountTextView = itemView.findViewById(R.id.like_count_text_view);

        titleTextView.setText(currentPost.getTitle());
        contentTextView.setText(currentPost.getContent());

        // timestamp 값을 Date 객체로 변환
        Date date = new Date(currentPost.getTimestamp());
        // 날짜 형식을 원하는 형태로 지정
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(date);

        timestampTextView.setText(formattedTimestamp);
        // 댓글 개수를 설정
        commentCountTextView.setText(String.valueOf(currentPost.getCommentCount()));
        //좋아요 개수를 설정
        likeCountTextView.setText(String.valueOf(currentPost.getLikeCount()));

        return itemView;
    }
}