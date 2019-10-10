package com.example.android.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.calcarithmatic.Calculator;

//simple calculator application by Erik P
public class MainActivity extends AppCompatActivity {
    private String displayString;
    private String outputDisplay;

    private String firstValue;
    private String secondValue;
    private String operation;
    private boolean firstFlag;
    private boolean trigFlag = false;

    private TextView calculationText;
    private TextView outputText;

    private Calculator calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calc = new Calculator();
        calculationText = findViewById(R.id.calculator_calculation);
        outputText = findViewById(R.id.calculator_output);

        //set specific onclick functionality for clear button
        //onclick remove last character and adjust values
        //onlongclick clear the whole screen
        Button clear = findViewById(R.id.clear_display);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(displayString.contains("*") || displayString.contains("/") || displayString.contains("+") || displayString.contains("-")){
                    firstFlag = true;
                    displayString = displayString.substring(0, displayString.length()-1);
                }
                else if(displayString.contains("(")){
                    reset();
                }
                else if(firstFlag && !firstValue.equals("")){
                    firstValue = firstValue.substring(0, firstValue.length()-1);
                    displayString = displayString.substring(0, displayString.length()-1);
                }
                else if(!secondValue.equals("")){
                    secondValue = secondValue.substring(0, secondValue.length()-1);
                    displayString = displayString.substring(0, displayString.length()-1);
                }
                else{

                }
                updateDisplay();
            }
        });
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                reset();
                updateDisplay();
                updateOutput();
                calc.clear();
                return false;
            }
        });

        //if the screen was rotated we want to restore the values that were currently being entered
        if(savedInstanceState != null){
            displayString = savedInstanceState.getString("display");
            outputDisplay = savedInstanceState.getString("output");
            firstValue = savedInstanceState.getString("firstVal");
            secondValue = savedInstanceState.getString("secondVal");
            firstFlag = savedInstanceState.getBoolean("firstFlag");
            trigFlag = savedInstanceState.getBoolean("trigFlag");
            operation = savedInstanceState.getString("operation");

            if(!operation.equals("")){
                calc.setOperation(operation);
            }
            updateDisplay();
            updateOutput();
        }
        else{
            reset();
        }
    }

    public void trigonometry(View view) {
        Button keypadButton = (Button)view;
        String trigFunc = keypadButton.getText().toString();
        if(firstFlag){
            displayString = trigFunc + "(";
            firstValue = trigFunc;
            firstFlag = false;
            updateDisplay();
            trigFlag = true;
        }
    }

    private void reset(){
        displayString = "";
        outputDisplay = "";
        firstValue = "";
        secondValue = "";
        firstFlag = true;
        trigFlag = false;
    }


    public void equals(View view) {
        if(trigFlag){
            Double output = calc.calculateTrigFunction(firstValue, secondValue);
            outputDisplay = String.valueOf(output);
            updateOutput();
            reset();
            updateDisplay();
            calc.clear();
        }
        else if(!firstFlag && secondValue != ""){
            Double output;
            output = calc.putNumber(firstValue);
            output = calc.putNumber(secondValue);
            outputDisplay = String.valueOf(output);
            updateOutput();
            reset();
            updateDisplay();
            calc.clear();
        }
        else{
            //do nothing
        }
    }


    public void number(View view) {
        Button keypadButton = (Button)view;
        String value = keypadButton.getText().toString();

        if(firstFlag){
            displayString += value;
            firstValue += value;
            updateDisplay();
        }
        else{
            displayString += value;
            secondValue += value;
            updateDisplay();
        }

    }

    public void operation(View view) {
        Button keypadButton = (Button)view;
        String operator = keypadButton.getText().toString();
        if(firstFlag && firstValue != ""){
            displayString += operator;
            operation = operator;
            updateDisplay();
            calc.setOperation(operation);
            firstFlag = false;
        }
        else{
            //do nothing
        }
    }

    public void addDot(View view) {
        if(!firstValue.contains(".") && firstFlag){
            displayString += ".";
            firstValue += ".";
        }
        else if(!secondValue.contains(".") && !firstFlag){
            displayString += ".";
            secondValue += ".";
        }
        else{
            //do nothing, don't add another .
        }
        updateDisplay();
    }

    private void updateDisplay(){
        calculationText.setText(displayString);

    }

    private void updateOutput(){
        outputText.setText(outputDisplay);
        outputText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", displayString);
        outState.putString("output", outputDisplay);
        outState.putString("firstVal", firstValue);
        outState.putString("secondVal", secondValue);
        outState.putString("operation", operation);
        outState.putBoolean("firstFlag", firstFlag);
        outState.putBoolean("trigFlag", trigFlag);
    }
}
