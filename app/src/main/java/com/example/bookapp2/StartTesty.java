package com.example.bookapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StartTesty extends AppCompatActivity {

    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4, nextBtn;

    private Timer quizTimer;
    private int seconds = 0;
    private int totalTimeInMins = 1;

    private final List<QuestionsList> questionsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_testy);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.selectedTopicName);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);

        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");
        selectedTopicName.setText(getSelectedTopic);

        startTimer(timer);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(StartTesty.this, Testyyy.class));
                finish();
            }
        });

    }



    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0){
                    totalTimeInMins--;
                    seconds = 59;
                } else if (seconds == 0 && totalTimeInMins == 0){

//                    totalTimeInMins = totalTimeInMins -1; // или  totalTimeInMins--
//                    seconds = 59;



                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(StartTesty.this, "Время вышло!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(StartTesty.this, TestResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("inCorrect", getInCorrectAnswers());

                    startActivity(intent);
                    finish();
                } else {
                    seconds --;
                }

                //метод для таймера (запустить новый поток)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() == 1){
                            finalMinutes = "0" + finalMinutes;
                        }
                        if (finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }

                        timerTextView.setText(finalMinutes + ":" + finalSeconds);

                    }
                });
            }
        }, 1000, 50);
    }

    //метод для правильных ответов
    private int getCorrectAnswers (){
        int correctAnswer = 0;
        for (int i = 0; i< questionsList.size(); i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer ++;
            }
        }
        return correctAnswer;

    }

    //метод для неправильных ответов
    private int getInCorrectAnswers (){
        int inCorrectAnswer = 0;
        for (int i = 0; i< questionsList.size(); i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if (!getUserSelectedAnswer.equals(getAnswer)){
                inCorrectAnswer ++;
            }
        }
        return inCorrectAnswer;

    }


}