package com.activity.toastactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText midtermGr, finalGr;
    Spinner spinSubs;
    Button compute, view;
    TextView ave;
    String[] subjects={"Mobile App","JAVA","HTML","SAD"};
    Double result;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        midtermGr=(EditText)findViewById(R.id.midtermGrade);
        finalGr=(EditText)findViewById(R.id.finalGrade);
        spinSubs=(Spinner) findViewById(R.id.spinnerSubs);
        compute=(Button)findViewById(R.id.compBtn);
        ave=(TextView)findViewById(R.id.average);
        view=(Button)findViewById(R.id.viewBtn);
        ArrayAdapter<String> subjectAdapter= new
                ArrayAdapter<String>(this,R.layout.spinsubs_layout,subjects);
        spinSubs.setAdapter(subjectAdapter);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = calculateResult();
                output = String.format("%s", result);
                ave.setText(output);
                if (result >= 75) {
                    ave.setBackgroundColor(Color.parseColor("#378805"));
                }
                else {
                    ave.setBackgroundColor(Color.parseColor("#FF0000"));
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = spinSubs.getSelectedItem().toString();
                String mid = midtermGr.getText().toString();
                String fin = finalGr.getText().toString();
                Toast toast = Toast.makeText(MainActivity.this, "SUBJECT: " +sub+
                        "\nMIDTERM: " +mid+ "\nFINAL: " +fin+ "\nAVERAGE: " +result,Toast.LENGTH_LONG);
                View toastView = toast.getView();
                TextView toastMessage = (TextView)
                        toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(20);

                toastMessage.setTextColor(Color.WHITE);
                toastMessage.setGravity(View.TEXT_ALIGNMENT_CENTER);
                toastMessage.setCompoundDrawablePadding(16);
                toastView.setBackgroundColor(Color.DKGRAY);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
    private double calculateResult() {
        try {
            double a = Double.parseDouble(midtermGr.getText().toString());
            double b = Double.parseDouble(finalGr.getText().toString());
            // Result
            return (a+b)/2;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
