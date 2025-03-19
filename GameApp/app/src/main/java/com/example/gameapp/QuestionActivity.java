package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity extends AppCompatActivity {
    // View variable
    TextView tv_question;
    EditText et_answer;
    Button bt_next, bt_submit;

    // Other variable
    int turnCounter = 0, score = 0;
    double actualAnswer = 0;
    String summary = "";
    String operator = "addition"; // default mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assign by view id
        tv_question = findViewById(R.id.tv_question);
        et_answer = findViewById(R.id.et_answer);
        bt_next = findViewById(R.id.bt_next);
        bt_submit = findViewById(R.id.bt_submit);

        //
        Intent intentOperator = getIntent();
        operator = intentOperator.getStringExtra("operator");

        generateQuestionBasedOnOperator();

        // Button to generate a random addition math question
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestionBasedOnOperator();
            }
        });

        // Press submit first will crash cuz "et_answer" is Empty
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the timer here if u want to time the user, use System time
                long startTime = System.currentTimeMillis();

                // Check if submission is empty
                if (et_answer.getText().toString().isEmpty()) {
                    Toast.makeText(QuestionActivity.this, "No empty answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parsing operation, cant cast String to int
                boolean isCorrectAnswer = false;
                if (operator.equals("division")) {
                    double userAnswerDouble = Double.parseDouble(et_answer.getText().toString());
                    double roundAnswerDouble = (float) Math.round(userAnswerDouble * 100.0) / 100.0;        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
                    isCorrectAnswer = Math.abs(roundAnswerDouble - actualAnswer) < 0.000001;                // https://www.baeldung.com/java-comparing-doubles
                }
                else {
                    int userAnswerInt = Integer.parseInt(et_answer.getText().toString());
                    isCorrectAnswer = userAnswerInt == (int) actualAnswer;
                }


                // Pre-check answer
                summary += "" + et_answer.getText().toString()  + " : " + (isCorrectAnswer? " Right Answer" : " Wrong Answer");

                // Change the TextView
                if (isCorrectAnswer) {
                    tv_question.setText("RIGHT!!");
                    score++;
                    Log.d("score", "Score is: " + score);
                    // Append String of the question and answer
                }
                else {
                    tv_question.setText("WRONG!");
                }

                // ??? Must put after code block above
                et_answer.setText("");

                // Turn counter event and Intent to send data
                Intent intentSummary = new Intent(QuestionActivity.this, SummaryActivity.class);    // TIL: Intent cant be put globally
                turnCounter++;
                if (turnCounter == 3) {
                    long endTime = System.currentTimeMillis();
                    //
                    intentSummary.putExtra("key_score", score);
                    intentSummary.putExtra("key_summary", summary);
                    intentSummary.putExtra("key_time", endTime - startTime);

                    startActivity(intentSummary);
                }
            }
        });
    }
    // Method to generate a new question based on the operator
    void generateQuestionBasedOnOperator() {
        if (operator.equals("random")) {
            int random = (int) (Math.random() * 4);

            switch (random) {
                case 0:
                    questionAddition();
                    break;
                case 1:
                    questionSubtraction();
                    break;
                case 2:
                    questionMultiplication();
                    break;
                case 3:
                    questionDivision();
                    break;
            }
        }
        else {
            switch (operator) {
                case "addition":
                    questionAddition();
                    break;
                case "subtraction":
                    questionSubtraction();
                    break;
                case "multiplication":
                    questionMultiplication();
                    break;
                case "division":
                    questionDivision();
                    break;
                default:
                    questionAddition();
            }
        }
    }

    // Do the thing br_next do, generate random addition math question
    void questionAddition() {
        int a = (int) (Math.random() * 21);
        int b = (int) (Math.random() * 21);
        actualAnswer = a + b;
        tv_question.setText("What is " + a + " + " + b + " ?");
        summary += "\n" + a + " + " + b + " = ";
    }

    // Do the thing br_next do, generate random addition math question
    void questionSubtraction() {
        // int a = 3, b = 5;                    // Debug to test
        int a = (int) (Math.random() * 21);
        int b = (int) (Math.random() * 21);
        // Check if a < b
        while (a < b) {
            a = (int) (Math.random() * 21);
            b = (int) (Math.random() * 21);
        }
        actualAnswer = a - b;
        tv_question.setText("What is " + a + " - " + b + " ?");
        summary += "\n" + a + " - " + b + " = ";
    }

    // Do the thing br_next do, generate random addition math question
    void questionMultiplication() {
        int a = (int) (Math.random() * 21);
        int b = (int) (Math.random() * 21);
        actualAnswer = a * b;
        tv_question.setText("What is " + a + " * " + b + " ?");
        summary += "\n" + a + " * " + b + " = ";
    }

    // Do the thing br_next do, generate random addition math question
    void questionDivision() {
        // int a = 8, b = 7;                    // Debug to test
        // int b = 0;                           // Debug to test
        int a = (int) (Math.random() * 21);
        int b = (int) (Math.random() * 21);
        // Check if b != 0
        while (b == 0) {
            b = (int) (Math.random() * 21);
        }
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        double actualAnswerDouble = (double)a / b;
        actualAnswer = Math.round(actualAnswerDouble * 100.0) / 100.0;

        tv_question.setText("What is " + a + " / " + b + " ?");
        summary += "\n" + a + " / " + b + " = ";
    }
}