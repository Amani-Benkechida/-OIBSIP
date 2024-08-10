package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView currentQuestionTextView;
    Button answerButtonA, answerButtonB, answerButtonC, answerButtonD;
    Button submitButton;

    int quizScore = 0;
    int totalQuestions = QuestionAnswer.question.length;
    int questionIndex = 0;
    String chosenAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        currentQuestionTextView = findViewById(R.id.question);
        answerButtonA = findViewById(R.id.ans_A);
        answerButtonB = findViewById(R.id.ans_B);
        answerButtonC = findViewById(R.id.ans_C);
        answerButtonD = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submit_btn);

        answerButtonA.setOnClickListener(this);
        answerButtonB.setOnClickListener(this);
        answerButtonC.setOnClickListener(this);
        answerButtonD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestions);

        loadNextQuestion();
    }

    @Override
    public void onClick(View view) {

        answerButtonA.setBackgroundColor(Color.WHITE);
        answerButtonB.setBackgroundColor(Color.WHITE);
        answerButtonC.setBackgroundColor(Color.WHITE);
        answerButtonD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (chosenAnswer.equals(QuestionAnswer.correctAnswers[questionIndex])) {
                quizScore++;
            }
            questionIndex++;
            loadNextQuestion();
        } else {
            // Choice button clicked
            chosenAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }
    }

    void loadNextQuestion() {

        if (questionIndex == totalQuestions) {
            completeQuiz();
            return;
        }

        currentQuestionTextView.setText(QuestionAnswer.question[questionIndex]);
        answerButtonA.setText(QuestionAnswer.choices[questionIndex][0]);
        answerButtonB.setText(QuestionAnswer.choices[questionIndex][1]);
        answerButtonC.setText(QuestionAnswer.choices[questionIndex][2]);
        answerButtonD.setText(QuestionAnswer.choices[questionIndex][3]);
    }

    void completeQuiz() {
        String resultStatus = "";
        if (quizScore > totalQuestions * 0.60) {
            resultStatus = "Passed";
        } else {
            resultStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(resultStatus)
                .setMessage("Score is " + quizScore + " out of " + totalQuestions)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        quizScore = 0;
        questionIndex = 0;
        loadNextQuestion();
    }
}
