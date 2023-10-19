package com.biniyam.calculatorapi_biniyam;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

        private EditText editTextNumber;
        private EditText editTextNumber2;
        private TextView resultTextView;
        private Button calculateButton;
        private CalculatorApi calculatorApi;
        private String operation; // Variable to store the selected operation

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editTextNumber = findViewById(R.id.editTextNumber);
            editTextNumber2 = findViewById(R.id.editTextNumber2);
            resultTextView = findViewById(R.id.textView);
            calculateButton = findViewById(R.id.calculateButton);


            // Initialize Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/") // Replace with your backend API URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            calculatorApi = retrofit.create(CalculatorApi.class);


            // Set operation when addition button is clicked
            Button additionButton = findViewById(R.id.additionButton);
            additionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operation = "addition";
                }
            });

            Button subtractionButton = findViewById(R.id.subtractionButton);
            subtractionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operation = "subtraction";
                }
            });

            Button multiplicationButton = findViewById(R.id.multiplicationButton);
            multiplicationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operation = "multiplication";
                }
            });

            Button divisionButton = findViewById(R.id.divisionButton);
            divisionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operation = "division";
                }
            });




            // Handle Calculate button click
            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (operation == null) {
                        Toast.makeText(MainActivity.this, "Select an operation first", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Get values from EditText fields
                    double firstNumber = Double.parseDouble(editTextNumber.getText().toString());
                    double secondNumber = Double.parseDouble(editTextNumber2.getText().toString());

                    // Create CalculationRequest object
                    CalculationRequest request = new CalculationRequest(firstNumber, secondNumber, operation);

                    // Make the API request
                    Call<Double> call = calculatorApi.performCalculation(request);
                    call.enqueue(new Callback<Double>() {
                        @Override
                        public void onResponse(Call<Double> call, Response<Double> response) {
                            if (response.isSuccessful()) {
                                Double result = response.body();
                                // Update the TextView with the result
                                resultTextView.setText("Result: " + result);
                            } else {
                                // Handle unsuccessful response
                                Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Double> call, Throwable t) {
                            // Handle network errors or other failures
                            Toast.makeText(MainActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }
    }


