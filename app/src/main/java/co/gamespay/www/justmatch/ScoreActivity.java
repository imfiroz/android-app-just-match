package co.gamespay.www.justmatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView finalScore = findViewById(R.id.finalScore);
        ImageButton btn_share = findViewById(R.id.btn_share);

        final int score = getIntent().getIntExtra("score", 0);
        finalScore.setText("Your Score :" + score);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_TEXT, "Your high Score on Just Match is "+ score);
                startActivity(intent);
            }
        });

    }
}
