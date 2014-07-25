package com.augmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceCaptureActivity extends Activity implements IAudioDoneCallback {

    SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.voice_capture);

        startListening();
    }

    private void startListening() {
        AugmateRecognitionListener listener = new AugmateRecognitionListener(this);

        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                .putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                .putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName())
                .putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    public void onSuccess(ArrayList<String> results){
        Toast.makeText(getApplicationContext(),
                TextUtils.join(", ",results) , Toast.LENGTH_LONG).show();
    }

}