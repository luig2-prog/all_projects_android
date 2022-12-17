package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private Button btn;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        btn = (Button) findViewById(R.id.button);
    }

    private int cont = 0;

   public void click(View v){
        switch (v.getId()){
            case R.id.button:

                String name = editText.getText().toString();
                textView.setText(name);
                //if(cont == 0){
                  //  textView.setText("0");
                   // cont = 1;
                //}else if(cont == 1){
                  //  textView.setText("1");
                   // cont = 0;
                //}
                break;
        }
   }

}