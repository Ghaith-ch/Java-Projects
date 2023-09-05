package com.example.labb1_v2_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText number1,number2;
    TextView result;
    Button sum,sub,multi,divi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1=findViewById(R.id.nr_1);
        number2=findViewById(R.id.nr_2);
        result=findViewById(R.id.txtview_result);

        sum=findViewById(R.id.btn_sum);
        sub=findViewById(R.id.btn_sub);
        multi=findViewById(R.id.btn_multi);
        divi=findViewById(R.id.btn_div);
    }

    public void sum(View view) {
        sum.setBackgroundColor(Color.rgb(164, 198, 57));
        if(!number1.getText().toString().equals("") && !number2.getText().toString().equals("")){
            double first_number =Double.parseDouble(number1.getText().toString());
            double second_number =Double.parseDouble(number2.getText().toString());
            result.setText("The Result is "+ (first_number + second_number));
        }
        else
            result.setText("Enter a number i both fields");
    }

    public void sub(View view) {
        sub.setBackgroundColor(Color.rgb(164, 198, 57));
        if(!number1.getText().toString().equals("") && !number2.getText().toString().equals("")){
            double first_number =Double.parseDouble(number1.getText().toString());
            double second_number =Double.parseDouble(number2.getText().toString());
            if(first_number > second_number){
                result.setText("The Result is "+ (first_number - second_number));
            }
            else {
                result.setText("The second number is bigger than first number");
            }
        }
        else
            result.setText("Enter a number i both fields");
    }

    public void multi(View view) {
        multi.setBackgroundColor(Color.rgb(164, 198, 57));
        if(!number1.getText().toString().equals("") && !number2.getText().toString().equals("")){
            double first_number =Double.parseDouble(number1.getText().toString());
            double second_number =Double.parseDouble(number2.getText().toString());
            result.setText("The Result is "+ (first_number * second_number));
        }
        else
            result.setText("Enter a number i both fields");
    }

    public void divi(View view) {
        divi.setBackgroundColor(Color.rgb(164, 198, 57));
        if(!number1.getText().toString().equals("") && !number2.getText().toString().equals("")){
            double first_number =Double.parseDouble(number1.getText().toString());
            double second_number =Double.parseDouble(number2.getText().toString());
            if(second_number !=0){
                result.setText("The Result is "+ (first_number / second_number));
            }
            else {
                result.setText("Cannot divide by zero");
            }
        }
        else
            result.setText("Enter a number i both fields");
    }

    public void clear(View view) {
        result.setText("Result");
        number1.setText("");
        number2.setText("");

        sum.setBackgroundColor(Color.rgb(66, 66, 66));
        sub.setBackgroundColor(Color.rgb(66, 66, 66));
        multi.setBackgroundColor(Color.rgb(66, 66, 66));
        divi.setBackgroundColor(Color.rgb(66, 66, 66));

    }


}