package com.example.calculator4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTV,solutionTV;
    MaterialButton buttonC,buttonAC,buttonOpenBracket,buttonCloseBracket;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV=findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);

        assignID(buttonC,R.id.buttons_c);
        assignID(buttonAC,R.id.buttons_ac);
        assignID(buttonOpenBracket,R.id.buttons_open_bracket);
        assignID(buttonCloseBracket,R.id.buttons_close_bracket);
        assignID(buttonDivide,R.id.buttons_divide);
        assignID(buttonMultiply,R.id.buttons_multiply);
        assignID(buttonPlus,R.id.buttons_add);
        assignID(buttonMinus,R.id.buttons_subtract);
        assignID(buttonEqual,R.id.buttons_equal);
        assignID(button0,R.id.buttons_0);
        assignID(button1,R.id.buttons_1);
        assignID(button2,R.id.buttons_2);
        assignID(button3,R.id.buttons_3);
        assignID(button4,R.id.buttons_4);
        assignID(button5,R.id.buttons_5);
        assignID(button6,R.id.buttons_6);
        assignID(button7,R.id.buttons_7);
        assignID(button8,R.id.buttons_8);
        assignID(button9,R.id.buttons_9);
        assignID(buttonDot,R.id.buttons_dot);

    }
    void assignID(MaterialButton button,int id){
        button=findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String btnText=button.getText().toString();
        String dataCalculate=solutionTV.getText().toString();
        if(btnText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        else if(btnText.equals("=")){
            String finalResult=calculate(dataCalculate);
            if(!finalResult.equals("E")){
                resultTV.setText(finalResult);
            }
            solutionTV.setText(resultTV.getText());
            return;
        }
        else if(btnText.equals("C")){
            dataCalculate=dataCalculate.substring(0, dataCalculate.length()-1);
        }else{
            dataCalculate=dataCalculate+btnText;
        }
        solutionTV.setText(dataCalculate);

    }

    String calculate(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String answer = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(answer.endsWith(".0")){
                answer=answer.replace(".0","");
            }
            return answer;
        }catch (Exception e){
            return "E";
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}