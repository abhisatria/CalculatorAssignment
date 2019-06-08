package com.example.abhiw.calculatorassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] item = {"Add","Substract","Divide","Multiply"};
    ArrayAdapter<String> adapter;
    Spinner sp;
    Button calculate;
    EditText et1,et2;
    TextView result;

    final int add=0;
    final int subs=1;
    final int div=2;
    final int mul=3;
    Double value1,value2;
    Double hasil=0.0;
    String opselected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.tvResult);
        et1=(EditText) findViewById(R.id.editText_value1);
        et2=(EditText) findViewById(R.id.editText_value2);
        sp=(Spinner) findViewById(R.id.sp1);
        calculate = (Button) findViewById(R.id.calculate);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opselected=item[position];
                Toast.makeText(getApplicationContext(),"You have selected " + opselected + " operation",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Please select operation",Toast.LENGTH_SHORT).show();
//                hasil=value1+value2;
//                result.setText(String.valueOf(hasil));
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1= Double.parseDouble(et1.getText().toString());
                value2= Double.parseDouble(et2.getText().toString());
                switch(opselected){
                    case "Add":
                        hasil= value1+value2;
                        result.setText(String.format("%.0f",hasil));
                        break;
                    case "Substract":
                        hasil=value1-value2;
                        result.setText(String.format("%.0f",hasil));
                        break;
                    case "Multiply":
                        hasil=value1*value2;
                        result.setText(String.format("%.0f",hasil));
                        break;
                    case "Divide":
                            hasil=value1/value2;
                            if(Double.isInfinite(hasil))
                            {
                                result.setText("Error");
                                Toast.makeText(getApplicationContext(),"Division by Zero",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(hasil==Math.round(hasil))
                                {
                                    result.setText(String.format("%.0f",hasil));
                                }
                                else
                                    result.setText(String.format("%.2f",hasil));
                            }
                        break;
                }
            }
        });

    }
}
