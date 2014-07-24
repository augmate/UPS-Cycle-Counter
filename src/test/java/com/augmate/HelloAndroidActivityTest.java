package com.augmate;

import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class HelloAndroidActivityTest {

    @Test
    public void shouldStartTheVoiceCaptureActivity() {
        Intent expectedActivity = new Intent(Robolectric.application, VoiceCaptureActivity.class);
        ActivityController<HelloAndroidActivity> controller = buildActivity(HelloAndroidActivity.class);

        controller.create();

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        assertThat(nextStartedActivity).isEqualTo(expectedActivity);
    }

}