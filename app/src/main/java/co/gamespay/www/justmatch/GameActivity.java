package co.gamespay.www.justmatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    TextView txtTime, txtScore, txtQuestion, txtAnswer;
    ImageButton btn_correct, btn_wrong;
    Boolean isResultCorrect;
    int seconds = 59;
    private int score = 0;
    private Boolean stopTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtQuestion     =   findViewById(R.id.txtQuestion);
        txtAnswer       =   findViewById(R.id.txtAnswer);
        txtTime         =   findViewById(R.id.txtTime);
        txtScore        =   findViewById(R.id.txtScore);
        btn_correct     =   findViewById(R.id.btn_correct);
        btn_wrong       =   findViewById(R.id.btn_wrong);

        timer();

        btn_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });
        btn_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });

        generateQuestion();
    }

    private void generateQuestion()
    {
        isResultCorrect = true;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int result = a + b;
        float f = random.nextFloat();
        if( f > 0.5f)
        {
            result = random.nextInt(100);
            isResultCorrect = false;
        }
        txtQuestion.setText(a + "+" + b);
        txtAnswer.setText("=" + result);

    }

    public void verifyAnswer(Boolean answer)
    {
        if(answer == isResultCorrect )
        {
            score+= 5;
            txtScore.setText("SCORE :" + score);
        }
        else
        {

            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        generateQuestion();
    }

    private void timer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable(){

            @Override
            public void run(){
                txtTime.setText("TIME :" +seconds);
                seconds--;
                if(seconds<0)
                {
                    Intent i = new Intent(GameActivity.this, ScoreActivity.class);
                    i.putExtra("Score", score);
                    stopTimer = true;
                    startActivity(i);
                }
                if(stopTimer == false)
                {
                    handler.postDelayed(this, 1000);
                }
            }

        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        stopTimer = false;
        finish();
    }
}
