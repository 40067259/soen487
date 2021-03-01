package com.concordia.a2.pojo;

public class Artist {
    private String nickname;
    private String firstname;
    private String lastname;
    private String bio;

    public Artist(String nickname, String firstname, String lastname, String bio) {
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
    }

    public Artist(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "nickname='" + nickname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
