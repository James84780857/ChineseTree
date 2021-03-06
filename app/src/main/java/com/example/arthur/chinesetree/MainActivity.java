package com.example.arthur.chinesetree;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.chinesetree.VoiceInput.VoiceInput;

import java.util.ArrayList;

import static com.example.arthur.chinesetree.R.menu.menu_main;

public class MainActivity extends AppCompatActivity {


    protected static final int RESULT_SPEECH = 1;

    private Button btnSpeak;
    private TextView txtText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtText = (TextView) findViewById(R.id.txtShow);

        btnSpeak = (Button) findViewById(R.id.btnVoice);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                }
                break;
            }

        }
    }



}
