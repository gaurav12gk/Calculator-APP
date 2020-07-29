package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
private EditText result;
private EditText newNumber;
private TextView displayOperation;
 private Double operand1=null;
// private  Double value
//=null;
 private  String pendingOperation="=";
 private final static  String state_pending_operation="Pending Operation";
 private  final static String state_operand1="Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result= findViewById(R.id.result);
        newNumber=(EditText) findViewById(R.id.newnumber);
        displayOperation=(TextView) findViewById(R.id.operation);
        Button button0=(Button) findViewById(R.id.button0);
        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        Button button4=(Button) findViewById(R.id.button4);
        Button button5=(Button) findViewById(R.id.button5);
        Button button6=(Button) findViewById(R.id.button6);
        Button button7=(Button) findViewById(R.id.button7);
        Button button8=(Button) findViewById(R.id.button8);
        Button button9=(Button) findViewById(R.id.button9);


        Button buttonplus=(Button) findViewById(R.id.plus);
        Button buttonminus=(Button) findViewById(R.id.minus);
        Button buttonmul=(Button) findViewById(R.id.mul);
        Button buttondivide=(Button) findViewById(R.id.divide);
        Button buttonequal=(Button) findViewById(R.id.equal);
        Button buttondot=(Button) findViewById(R.id.dot);
        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button b= (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);
View.OnClickListener oplistner=new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Button b=(Button) view;
        String op=b.getText().toString();
        String value=newNumber.getText().toString();
        try {
            Double doublevalue=Double.valueOf(value);
            performOperation(doublevalue,op);

        }catch (NumberFormatException e){
            newNumber.setText("");
        }
        pendingOperation=op;
        displayOperation.setText(pendingOperation);
    }
};
buttonequal.setOnClickListener(oplistner);

        buttonminus.setOnClickListener(oplistner);
        buttonplus.setOnClickListener(oplistner);
        buttonmul.setOnClickListener(oplistner);
        buttondivide.setOnClickListener(oplistner);

        Button neg=(Button) findViewById(R.id.neg);
 neg.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         String value=newNumber.getText().toString();
         if(value.length()==0)
             newNumber.setText("-");
     else
     {
         try
     {

         Double doublevalue=Double.valueOf(value);
          doublevalue*=-1;
          newNumber.setText(doublevalue.toString());
     }catch(NumberFormatException e) {
             newNumber.setText("");
         }}}
 });
 // NEG button ended ......................


        //Code for the clear button ......................................
       Button clear =findViewById(R.id.clear);
       clear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               newNumber.setText("");

               result.setText("");
               operand1=null;
           }
       });




  }

    @Override
    public void onSaveInstanceState( Bundle outState) {
       outState.putString(state_pending_operation,pendingOperation);
       if(operand1!=null)
           outState.putDouble(state_operand1,operand1);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation=savedInstanceState.getString(state_pending_operation);
        operand1=savedInstanceState.getDouble(state_operand1);
        displayOperation.setText(pendingOperation);
    }

    private  void performOperation(Double value, String operation)
    {
        if(null==operand1)
            operand1=(value);
        else {
            if(pendingOperation.equals("="))
            {
                pendingOperation=operation;
            }
            switch(pendingOperation){
            case "=":
                   operand1=value;
                   break;

                case"/":
                    if(value ==0.0)operand1=0.0;
                    else
                        operand1/=value;break;
                case"*":
                    operand1*=value;
                    break;
                case"+": operand1+=value;
                break;
                case "-": operand1-=value;
                break;
            }

        }result.setText(operand1.toString());
    newNumber.setText("");}
}