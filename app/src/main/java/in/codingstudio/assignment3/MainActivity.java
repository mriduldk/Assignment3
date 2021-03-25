package in.codingstudio.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import in.codingstudio.assignment3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.editTextDate1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    SelectDate1();
                }
            }
        });


        binding.editTextDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDate1();
            }
        });

        binding.editTextDate2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    SelectDate2();
                }
            }
        });


        binding.editTextDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDate2();
            }
        });


        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String strDate1 = binding.editTextDate1.getText().toString().trim();
                String strDate2 = binding.editTextDate2.getText().toString().trim();

                if (strDate1.equals("")){
                    binding.editTextDate1.setError("Date 1 is empty");
                    binding.editTextDate1.requestFocus();

                }else if (strDate2.equals("")){
                    binding.editTextDate2.setError("Date 2 is empty");
                    binding.editTextDate2.requestFocus();

                }else{

                    try {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = sdf.parse(strDate1);
                        Date date2 = sdf.parse(strDate2);

                        long difference = Objects.requireNonNull(date2).getTime() - Objects.requireNonNull(date1).getTime();

                        if (difference < 0){
                            difference = Objects.requireNonNull(date1).getTime() - Objects.requireNonNull(date2).getTime();
                        }

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                        alertDialog.setTitle("Result");
                        alertDialog.setMessage("Number of Days between the two Dates is " + TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS));

                        alertDialog.setNegativeButton("Got It !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alertDialog.show();

                    }catch (ParseException e){
                        e.printStackTrace();
                    }

                }

            }
        });



    }

    private void SelectDate1(){

        Calendar mcurrentTime = Calendar.getInstance();
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
        int month = mcurrentTime.get(Calendar.MONTH);
        int year = mcurrentTime.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                String day, month ;
                if (d < 10){
                    day = "0" + d;
                }else{
                    day = "" + d;
                }

                if (m < 10){
                    month = "0"+ (m+1);
                }else{
                    month = ""+ (m+1);
                }

                binding.editTextDate1.setText(y + "-" + month + "-" + day);

            }
        }, year, month, day);

        datePickerDialog.setTitle("Select Date 1");
        datePickerDialog.show();
    }

    private void SelectDate2(){

        Calendar mcurrentTime = Calendar.getInstance();
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
        int month = mcurrentTime.get(Calendar.MONTH);
        int year = mcurrentTime.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                String day, month ;
                if (d < 10){
                    day = "0" + d;
                }else{
                    day = "" + d;
                }

                if (m < 10){
                    month = "0"+ (m+1);
                }else{
                    month = ""+ (m+1);
                }

                binding.editTextDate2.setText(y + "-" + month + "-" + day);

            }
        }, year, month, day);

        datePickerDialog.setTitle("Select Date 2");
        datePickerDialog.show();
    }


}