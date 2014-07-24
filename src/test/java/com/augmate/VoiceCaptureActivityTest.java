package com.augmate;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class VoiceCaptureActivityTest {
    @Mock
    SpeechRecognizer speechRecognizer;
    private ActivityController<VoiceCaptureActivity> controller;

    @Before
    public void setup() {
        initMocks(this);
        controller = buildActivity(VoiceCaptureActivity.class);

        VoiceCaptureActivity activity = controller.get();
        activity.speechRecognizer = speechRecognizer;
    }

    @Test
    public void shouldSetSpeechRecognizer() {
        controller.create();

        verify(speechRecognizer).setRecognitionListener(any(RecognitionListener.class));
    }

    @Test
    public void shouldStartListeningWhenRecognitionIsAvailable() {
        controller.create();

        verify(speechRecognizer).startListening(any(Intent.class));
    }

    @Test
    public void shouldSetupStartListeningIntent() {
        Intent expected = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                .putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                .putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.augmate.ups.cycle")
                .putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);

        controller.create();

        verify(speechRecognizer).startListening(captor.capture());

        Intent actualIntent = captor.getValue();
        assertThat(actualIntent).isEqualTo(expected);
    }
 }