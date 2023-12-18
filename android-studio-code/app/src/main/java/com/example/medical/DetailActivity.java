package com.example.medical;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends Activity {

    public TextView titleTextView;
    public TextView contentTextView;
    public TextView timestampTextView;
    public TextView commentCountTextView;
    public TextView likeCountTextView;

    public EditText commentEditText;
    public Button commentButton;
    public ImageButton likeButton;
    public ListView commentListView;
    private DatabaseReference databaseReference;
    public CommentAdapter commentAdapter;


    private String postId;
    public String postTitle;
    public String postContent;
    public long postTimestamp;
    public int commentCount;
    private int likeCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_comment);

        titleTextView = findViewById(R.id.title_text_view);
        contentTextView = findViewById(R.id.content_text_view);
        timestampTextView = findViewById(R.id.timestamp_text_view);
        commentEditText = findViewById(R.id.comment_edit_text);
        commentButton = findViewById(R.id.comment_button);
        commentListView = findViewById(R.id.comment_list_view);
        commentCountTextView = findViewById(R.id.comment_count_text_view);
        likeCountTextView = findViewById(R.id.like_count_text_view);
        likeButton = findViewById(R.id.like_button);


        // Firebase Database의 "comments" 참조를 가져옴
        databaseReference = FirebaseDatabase.getInstance().getReference("comments");

        // 인텐트에서 게시글 제목과 내용을 가져옴
        postId = getIntent().getStringExtra("postId");
        postTitle = getIntent().getStringExtra("title");
        postContent = getIntent().getStringExtra("content");
        postTimestamp = getIntent().getLongExtra("timestamp", 0);

        // 가져온 제목과 내용을 화면에 표시
        titleTextView.setText(postTitle);
        contentTextView.setText(postContent);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedTimestamp = sdf.format(new Date(postTimestamp));
        timestampTextView.setText(formattedTimestamp);

        // 좋아요 개수를 표시
        likeCountTextView.setText(String.valueOf(likeCount));

        // 댓글 어댑터 초기화
        // Firebase Database에서 해당 게시글의 댓글 데이터를 가져와서 commentList에 저장
        List<Comment> commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, commentList);
        commentListView = findViewById(R.id.comment_list_view);
        commentListView.setAdapter(commentAdapter);

        // 댓글 등록 버튼 클릭 이벤트 처리
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(commentContent)) {
                    long timestamp = new Date().getTime();
                    // Firebase Realtime Database에 댓글 등록
                    DatabaseReference newCommentRef = databaseReference.push();
                    String commentId = newCommentRef.getKey();
                    if (commentId != null) {
                        // 댓글 객체 생성
                        Comment comment = new Comment(commentId, postId, commentContent, timestamp);
                        // 댓글 객체를 Firebase Realtime Database에 저장
                        newCommentRef.setValue(comment, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error == null) {
                                    Toast.makeText(DetailActivity.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DetailActivity.this, "댓글 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(DetailActivity.this, "댓글 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                    // 입력 필드 초기화
                    commentEditText.setText("");
                } else {
                    Toast.makeText(DetailActivity.this, "댓글 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 좋아요 버튼 클릭 이벤트 처리
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 좋아요 개수 증가
                likeCount++;
                // 좋아요 개수를 표시
                likeCountTextView.setText(String.valueOf(likeCount));
                // 좋아요 버튼 비활성화
                likeButton.setEnabled(false);
                // 좋아요 상태를 Firebase Realtime Database에 저장
                DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("posts").child(postId);
                postRef.child("likeCount").setValue(likeCount);
            }
        });

        // Firebase Realtime Database에서 댓글 데이터 변경을 감지하고, 화면에 업데이트
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    if (comment != null && comment.getPostId() != null && comment.getPostId().equals(postId)) {
                        commentList.add(comment);
                    }
                }
                commentAdapter.notifyDataSetChanged();

                // 댓글 개수 업데이트
                commentCount = commentList.size();
                commentCountTextView.setText(String.valueOf(commentCount));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailActivity.this, "댓글 목록을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 게시글의 좋아요 개수를 Firebase Realtime Database에서 가져와서 표시
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference("posts").child(postId);
        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Post post = snapshot.getValue(Post.class);
                    if (post != null) {
                        likeCount = post.getLikeCount();
                        likeCountTextView.setText(String.valueOf(likeCount));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailActivity.this, "게시글 정보를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}