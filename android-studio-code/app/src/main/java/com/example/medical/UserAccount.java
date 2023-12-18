package com.example.medical;
// 사용자 계정 정보 모델 클래스
public class UserAccount {
    private String idToken; //고유정보(유일 키 값)
    private String emailId;
    private String password;
    private String tel;
    private String name;
    private String nickname;

    public UserAccount() { }

    public String getIdToken() { return idToken; }

    public void setIdToken(String idToken) { this.idToken = idToken; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }
}
