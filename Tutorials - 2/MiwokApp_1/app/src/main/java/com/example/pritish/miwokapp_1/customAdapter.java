package com.example.pritish.miwokapp_1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class customAdapter extends ArrayAdapter<CustomModel> {

    private MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;

    public CustomModel customModel;

    public customAdapter(Context context, ArrayList<CustomModel> users){
        super(context, 0, users);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();

            } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayerObject();

            }
        }
    };

    @Override
    public View getView(final int position,
                        View convertView,
                        ViewGroup parent) {

        customModel = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.left_text);
        TextView tvHome = (TextView) convertView.findViewById(R.id.right_text);
        final Button playAudio = (Button) convertView.findViewById(R.id.soundButton);

        tvName.setText(customModel.name);
        tvHome.setText(customModel.hometown);

//        Reset mediaPlayer

//        Media Player


        playAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result != AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    return ;
                }
                checkIfMediaPlayerIsInitialised();

                mediaPlayer = MediaPlayer.create(getContext(), customModel.songUrl);

                Log.e("TAG PRITISH", "Got the click from position: " + position + customModel.isPlayingAudio);
                customModel.isPlayingAudio = !customModel.isPlayingAudio;
                if(customModel.isPlayingAudio){
                    mediaPlayer.start();
                    playAudio.setText("Pause");
                    Toast.makeText(getContext(), "Playing audio", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.pause();
                    playAudio.setText("Play");
                    Toast.makeText(getContext(), "Pausing Audio play", Toast.LENGTH_SHORT).show();
                }

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getContext(), "Played the audio file to the end", Toast.LENGTH_SHORT).show();
                        customModel.isPlayingAudio = false;
                        playAudio.setText("Play");
                        releaseMediaPlayerObject();
                    }
                });

            }
        });


        return convertView;
    }

    public void checkIfMediaPlayerIsInitialised(){
        if(mediaPlayer != null){
            Log.e("TAG PRITISH", "Existing Model present" + customModel.name);
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }

    public void releaseMediaPlayerObject(){
        Log.e("TAG PRITISH", "see the name" + customModel.name);
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

}
