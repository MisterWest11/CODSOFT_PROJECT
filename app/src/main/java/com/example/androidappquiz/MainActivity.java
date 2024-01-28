package com.example.androidappquiz;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //create references for all views created in xml file
    TextView quizAppTextView;
    TextView questionsTextView;
    Button btnA, btnB, btnC, btnD;
    Button btnSubmit;

    //create our class- level variables
    int score = 0;
    int currentQuestionIndex = 0;
    String selectedChoice = "";
    int totalQuestions = QuestionsAndAnswersFile.questions.length;
    Button lastSelectedButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign each view in mainActivity to the ones in xml file by id
        quizAppTextView = findViewById(R.id.quiz);
        questionsTextView = findViewById(R.id.quizQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnSubmit = findViewById(R.id.btnSubmit);

        //set onClickListener to all buttons
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        //when app is loaded, call method loadQuestion to load a new question
        loadQuestion();
    }

    //
    @Override
    public void onClick(View v) {


        Button clickedButton = (Button) v;
        String correctAnswer = QuestionsAndAnswersFile.correctChoices[currentQuestionIndex];

        if(clickedButton.getId()== R.id.btnSubmit){

            if(selectedChoice.equals(QuestionsAndAnswersFile.correctChoices[currentQuestionIndex])){
                getButtonByText(selectedChoice).setBackgroundColor(Color.GREEN);
                score++;
            } else {
                getButtonByText(selectedChoice).setBackgroundColor(Color.RED);
                getButtonByText(correctAnswer).setBackgroundColor(Color.GREEN);
            }
            currentQuestionIndex++;

            //add a delay before loading the next question
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    loadQuestion();
                }
            }, 1000);

        }
        else{

            //choice button has been clicked if a button was previously clicked, reset its color

            if(lastSelectedButton != null){
                lastSelectedButton.setBackgroundColor(Color.WHITE);
            }

            //highlight newly selected button
            clickedButton.setBackgroundColor(Color.GRAY);

            //update last selected button and selected choice
            lastSelectedButton = clickedButton;
            selectedChoice = clickedButton.getText().toString();

            
        }
    }

    void loadQuestion(){
        //load question from our separate file to the textView

        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }

        //reset button colors
        btnA.setBackgroundColor(Color.WHITE);
        btnB.setBackgroundColor(Color.WHITE);
        btnC.setBackgroundColor(Color.WHITE);
        btnD.setBackgroundColor(Color.WHITE);

        questionsTextView.setText(QuestionsAndAnswersFile.questions[currentQuestionIndex]);
        btnA.setText(QuestionsAndAnswersFile.choices[currentQuestionIndex][0]);
        btnB.setText(QuestionsAndAnswersFile.choices[currentQuestionIndex][1]);
        btnC.setText(QuestionsAndAnswersFile.choices[currentQuestionIndex][2]);
        btnD.setText(QuestionsAndAnswersFile.choices[currentQuestionIndex][3]);

    }

    private Button getButtonByText(String text){
        if(btnA.getText().toString().equals(text)){
            return btnA;
        } else if(btnB.getText().toString().equals(text)){
            return btnB;
        } else if(btnC.getText().toString().equals(text)){
            return btnC;
        } else if (btnD.getText().toString().equals(text)){
            return btnD;
        } else {
            return null;
        }
    }
    //create method to show final score and correct answers
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestions * 0.50){
            passStatus = "You have passed the quiz.";
        }
        else{
            passStatus = "You have failed the quiz.";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestions)
                .setPositiveButton("Restart Quiz", (dialogInterface, i) -> restartQuiz()  )
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadQuestion();
    }
}
