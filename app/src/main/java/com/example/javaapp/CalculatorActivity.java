package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        Button calculateButton = (Button) findViewById(R.id.Calculate_button);

        View.OnClickListener calculateButtonListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String WeightString = "Weight";
                String ScoreString = "Score";

                int[] Weights = new int[6];
                int[] Scores = new int[6];

                for (int i = 0; i < 6; i++)
                {

                    String WeightEditTextID_String = WeightString + Integer.toString(i);
                    String ScoreEditTextID_String = ScoreString + Integer.toString(i);

                    int WeightEditTextID_int = getResources().getIdentifier(WeightEditTextID_String, "id", "com.example.myapplication");
                    int ScoreEditTextID_int = getResources().getIdentifier(ScoreEditTextID_String, "id", "com.example.myapplication");

                    EditText WeightEditText = findViewById(WeightEditTextID_int);
                    EditText ScoreEditText = findViewById(ScoreEditTextID_int);

                    String WeightEditTextContent = WeightEditText.getText().toString();
                    String ScoreEditTextContent = WeightEditText.getText().toString();

                    if (WeightEditTextContent.equals("") || ScoreEditTextContent.equals(""))
                    {
                        Weights[i] = 0;
                        Scores[i] = 0;
                    }
                    else
                    {
                        Weights[i] = Integer.parseInt(WeightEditTextContent);
                        Scores[i] = Integer.parseInt(ScoreEditTextContent);
                    }

                }
                double GPA = Calculator.CalculateGPA(Weights, Scores);
                String GPA_STR = Double.toString(GPA);
                TextView GPATextView = findViewById(R.id.GPA_Text_View);

                if (!GPA_STR.equals(""))
                    GPATextView.setText(GPA_STR);
            }
        };
        calculateButton.setOnClickListener(calculateButtonListener);


    }
}