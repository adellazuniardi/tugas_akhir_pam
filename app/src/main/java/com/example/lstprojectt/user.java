package com.example.lstprojectt;

public class user {
    private String id, NamaBand, Genre, terbentuk, bio;

    public user(){

    }
    public user(String NamaBand, String Genre, String Terbentuk, String Biografi) {
        this.NamaBand = NamaBand;
        this.Genre = Genre;
        this.terbentuk = Terbentuk;
        this.bio = Biografi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaBand() {
        return NamaBand;
    }

    public void setNamaBand(String namaBand) {
        NamaBand = namaBand;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getTerbentuk() {
        return terbentuk;
    }

    public void setTerbentuk(String terbentuk) {
        this.terbentuk = terbentuk;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
