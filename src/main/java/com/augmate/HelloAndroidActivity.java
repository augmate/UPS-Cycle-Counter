package com.augmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.augmate.ups.cycle.R;

public class HelloAndroidActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, VoiceCaptureActivity.class);

        startActivity(intent);
    }
 }

