package com.example.pritish.miwokapp_1;

public class CustomModel {
    public String name;
    public String hometown;
    public int ImageId;
    public int songUrl;
    public boolean isPlayingAudio = false;

    public CustomModel(String name, String hometown, int ImageId, int songUrl){
        this.name = name;
        this.hometown = hometown;
        this.ImageId = ImageId;
        this.songUrl = songUrl;
    }
}
