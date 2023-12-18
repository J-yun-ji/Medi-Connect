package com.example.medical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        super(context, 0, commentList);
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        }

        Comment currentComment = commentList.get(position);

        TextView contentTextView = itemView.findViewById(R.id.comment_content_text_view);
        TextView timestampTextView = itemView.findViewById(R.id.comment_timestamp_text_view);

        contentTextView.setText(currentComment.getCommentContent());

        Date date = new Date(currentComment.getTimestamp());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(date);
        timestampTextView.setText(formattedTimestamp);

        return itemView;
    }
}
class Comment {
    private String commentId;
    private String postId;
    private String commentContent;
    private long timestamp;

    public Comment() {
        // 기본 생성자
    }

    public Comment(String commentId, String postId, String commentContent, long timestamp) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.timestamp = timestamp;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

