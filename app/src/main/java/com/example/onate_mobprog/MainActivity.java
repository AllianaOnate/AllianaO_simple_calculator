package com.example.onate_mobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStore;

import android.opengl.Matrix;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTextView,solutionTextView;
    MaterialButton buttonC, buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonDivision, buttonMultiplication, buttonSubtraction, buttonAddition, buttonEquals;
    MaterialButton buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine;
    MaterialButton buttonAutoCancel, buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultTextView = findViewById(R.id.result_text_view);
       solutionTextView = findViewById(R.id.solution_text_view);

       assignId(buttonC,R.id.button_c_cancel);
       assignId(buttonBracketOpen,R.id.button__open_bracket);
       assignId(buttonBracketClose,R.id.button__close_bracket);
       assignId(buttonDivision,R.id.button_division_symbol);
       assignId(buttonMultiplication,R.id.button_multiplication_symbol);
       assignId(buttonSubtraction,R.id.button_subtraction_symbol);
       assignId(buttonAddition,R.id.button_addition_symbol);
       assignId(buttonEquals,R.id.button_equals_symbol);
       assignId(buttonZero,R.id.button_zero);
       assignId(buttonOne,R.id.button_one);
       assignId(buttonTwo,R.id.button_two);
       assignId(buttonThree,R.id.button_three);
       assignId(buttonFour,R.id.button_four);
       assignId(buttonFive,R.id.button_five);
       assignId(buttonSix,R.id.button_six);
       assignId(buttonSeven,R.id.button_seven);
       assignId(buttonEight,R.id.button_eight);
       assignId(buttonNine,R.id.button_nine);
       assignId(buttonAutoCancel,R.id.button_auto_cancel);
       assignId(buttonDot,R.id.button_dot);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTextView.getText().toString();

        if(buttonText.equals("AC")){
            solutionTextView.setText(" ");
            resultTextView.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTextView.setText(resultTextView.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;

        }
        solutionTextView.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTextView.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable Scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(Scriptable, data, "Javascript", 1, null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}