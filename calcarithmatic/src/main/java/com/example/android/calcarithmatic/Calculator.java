package com.example.android.calcarithmatic;

import java.util.ArrayList;

//simple calculator, must instantiate object, set an operator to be done and pass it two numbers
public class Calculator {
    private String operation;
    private ArrayList<Double> inputs;

    public Calculator(){
        inputs = new ArrayList<>();
    }

    public void clear(){
        operation = null;
        inputs.clear();
    }

    //calculates based on the set operator, after processing answer it stores the answer in the
    //first index of the array.  Could be used in the future to constantly hold the current value until cleared
    public Double putNumber(String in){
        Double returnVal = Double.valueOf(in);
        inputs.add(Double.valueOf(in));
        if(inputs.size() > 1){
            Double input1 = inputs.get(0);
            Double input2 = inputs.get(1);
            switch(operation){
                case "/":
                    returnVal = input1 / input2;
                    break;
                case "*":
                    returnVal = input1 * input2;
                    break;
                case "+":
                    returnVal = input1 + input2;
                    break;
                case "-":
                    returnVal = input1 - input2;
                    break;
                default:
                    break;
            }
            inputs.set(0, returnVal);
            inputs.remove(1);
        }
        return returnVal;
    }

    public void setOperation(String in){
        operation = in;
    }

    //handles the sin/cos/tan functionality
    public Double calculateTrigFunction(String trig, String number){
        Double num = Double.valueOf(number);
        Double output = null;
        switch (trig){
            case "Sin":
                output = Math.sin(num);
                break;
            case "Cos":
                output = Math.cos(num);
                break;
            case "Tan":
                output = Math.tan(num);
                break;
            default:
                break;
        }
        return output;
    }
}
