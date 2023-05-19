package com.auto.sequences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SequenceActivity extends AppCompatActivity {

    private static Map<Integer, Long> triboCache = new HashMap<>();
    private static Map<Integer, Long> fibonacciCache = new HashMap<>();
    private static Map<Integer, Long> lucasCache = new HashMap<>();

    private ImageButton backBtn;
    private Button calcBtn, clearBtn;
    private View view;

    private EditText input, input2, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sequence);

        // Retrieve the data from the intent
        String sequenceName = getIntent().getStringExtra("sequenceName");
        String sequenceDescription = getIntent().getStringExtra("sequenceDescription");
        String sequenceFind = getIntent().getStringExtra("sequenceFind");
        String sequenceForm = getIntent().getStringExtra("sequenceForm");

        // Set the data to the appropriate views
        TextView sequencesNameTextView = findViewById(R.id.sequences_name);
        sequencesNameTextView.setText(sequenceName);

        TextView sequenceDescTextView = findViewById(R.id.seqeunce_desc);
        sequenceDescTextView.setText(sequenceDescription);

        TextView sequenceFindTextView = findViewById(R.id.sequence_find);
        sequenceFindTextView.setText(sequenceFind);

        TextView sequenceFormTextView = findViewById(R.id.sequence_form);
        sequenceFormTextView.setText(sequenceForm);

        //Set view
        view = findViewById(android.R.id.content);

        //Set IDs to edit text
        input = findViewById(R.id.input);
        input2 = findViewById(R.id.input2);

        //Set visibility to second input to GONE only show with Euclidean
        int input2Visibility = getIntent().getIntExtra("input2Visibility", View.GONE);
        input2.setVisibility(input2Visibility);

        output = findViewById(R.id.output);
        //Set edit text not clickable or editable
        output.setEnabled(false);

        //Set IDs and actions to buttons
        calcBtn = findViewById(R.id.calc_btn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if input is empty
                String inputText = input.getText().toString().trim();
                if (inputText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the input value
                int inputValue = Integer.parseInt(inputText);

                switch (sequenceName) {
                    case "Euclidean Algorithm":
                        // Check if second input is empty
                        String inputText2 = input2.getText().toString().trim();
                        if (inputText2.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Second input cannot be empty.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int inputValue2 = Integer.parseInt(inputText2);

                        // Check if inputValue is greater than inputValue2
                        if (inputValue <= inputValue2) {
                            Toast.makeText(getApplicationContext(), "A must be greater than B.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Call method to calculate GCD and LCM
                        euclidean(inputValue, inputValue2);

                        break;
                    case "Fibonacci Sequence":
                        //Call method to calculate fibonacci sequence
                        fibonacci(inputValue);

                        if (inputValue < 0) {
                            // Show a warning message using a Snackbar
                            Toast.makeText(getApplicationContext(), "Negative values are not accepted.", Toast.LENGTH_LONG).show();
                        } else {
                            StringBuilder sb = new StringBuilder("Fn = ");
                            for (int i = 0; i <= inputValue; i++) {
                                sb.append(fibonacci(i)).append(", ");
                            }

                            sb.deleteCharAt(sb.length() - 1); // remove last comma
                            sb.deleteCharAt(sb.length() - 1); // remove space before "for"
                            sb.append(" \nfor 0 <= n <= ").append(inputValue); // add additional text
                            output.setText(sb.toString());
                        }

                        break;
                    case "Collatz Sequence":
                        //Call method to calculate collatz sequence
                        collatz(inputValue);

                        break;
                    case "Tribonacci Sequence":
                        //Call method to calculate tribonacci sequence
                        tribonacci(inputValue);

                        if (inputValue < 0) {
                            // Show a warning message using JOptionPane
                            Toast.makeText(getApplicationContext(), "Negative values are not accepted.", Toast.LENGTH_LONG).show();
                        } else {
                            StringBuilder sb = new StringBuilder("Tn = ");
                            for (int i = 0; i <= inputValue; i++) {
                                sb.append(tribonacci(i)).append(", ");
                            }
                            sb.deleteCharAt(sb.length() - 1); // remove last comma
                            sb.deleteCharAt(sb.length() - 1); // remove space before "for"
                            sb.append(" \nfor 0 <= n <= ").append(inputValue); // add additional text
                            // System.out.println(sb);
                            output.setText(sb.toString());
                        }

                        break;
                    case "Lucas Numbers":
                        //Call method to calculate lucas sequence
                        lucas(inputValue);

                        if (inputValue < 0) {
                            // Show a warning message using JOptionPane
                            Toast.makeText(getApplicationContext(), "Negative values are not accepted.", Toast.LENGTH_LONG).show();
                        } else {
                            StringBuilder sb = new StringBuilder("Ln = ");
                            for (int i = 0; i <= inputValue; i++) {
                                sb.append(lucas(i)).append(", ");
                            }
                            sb.deleteCharAt(sb.length() - 1); // remove last comma
                            sb.deleteCharAt(sb.length() - 1); // remove space before "for"
                            sb.append(" \nfor 0 <= n <= ").append(inputValue); // add additional text

                            output.setText(sb.toString());
                        }

                        break;
                    default:
                        // Handle unrecognized sequence
                        break;
                }
            }
        });

        clearBtn = findViewById(R.id.clr_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                input2.setText("");
                output.setText("");
            }
        });

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // FUNCTIONS TO CALCULATE SEQUENCES
    public static long fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // Check if the result is already calculated and stored in the cache
        if (fibonacciCache.containsKey(n)) {
            return fibonacciCache.get(n);
        }

        // Calculate the Fibonacci number using recursion
        long result = fibonacci(n - 1) + fibonacci(n - 2);

        // Store the result in the cache
        fibonacciCache.put(n, result);

        return result;
    }

    public static long lucas(int n) {
        if (n == 0) {
            return 2L;
        } else if (n == 1) {
            return 1L;
        }

        // Check if the result is already calculated and stored in the cache
        if (lucasCache.containsKey(n)) {
            return lucasCache.get(n);
        }

        // Calculate the Lucas number using recursion
        long result = lucas(n - 1) + lucas(n - 2);

        // Store the result in the cache
        lucasCache.put(n, result);

        return result;
    }

    private static Long tribonacci(int n) {
        if (triboCache.containsKey(n)) {
            return triboCache.get(n);
        }

        long result;

        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 0;
        } else if (n == 2) {
            result = 1;
        } else {
            result = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }

        triboCache.put(n, result);
        return result;

    }

    private void collatz(int number) {
        StringBuilder sequence = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        StringBuilder solution = new StringBuilder();

        answer.append("\nCollatz Sequence: \n").append(number);

        int i = 0;
        while (number != 1) {
            if (number % 2 == 0) {
                sequence.append("C[").append(i).append("] = ").append(number).append("(E)\n\n");

                sequence.append("C[").append(i + 1).append("] = ").append(number).append("/2 = ");
                number /= 2;
            } else {
                sequence.append("C[").append(i).append("] = ").append(number).append("(O)\n\n");

                sequence.append("C[").append(i + 1).append("] = 3(").append(number).append(") + 1 = ");
                number = 3 * number + 1;
            }
            sequence.append(number).append("\n");
            answer.append(", ").append(number);
            i++;
        }

        solution.append(sequence).append(answer);

        output.setText(solution.toString());
    }



    //CALCULATE EUCLIDEAN ALGORITHM
    private void euclidean(int inputValue, int inputValue2) {
        // Check if input is non-negative
        if (inputValue < 0 || inputValue2 < 0) {
            Toast.makeText(this, "Numbers must be non-negative.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate GCD and LCM
        int gcd = calculateGcd(inputValue, inputValue2);
        int lcm = calculateLcm(inputValue, inputValue2, gcd);

        // Generate the solution format
        StringBuilder solution = new StringBuilder();
        solution.append("A = ").append(inputValue).append("   B = ").append(inputValue2).append("\n");
        solution.append("\nSolution:\n\n");
        solution.append(generateSolutionSteps(inputValue, inputValue2));

        // Display the results
        String resultMessage = solution.toString() + "\nGCD = " + gcd + "\nLCM = " +"("+inputValue+"*"+inputValue2+")/"+gcd +" = " + lcm;
        output.setText(resultMessage);
    }

    private int calculateGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return calculateGcd(b, a % b);
        }
    }

    private int calculateLcm(int a, int b, int gcd) {
        return Math.abs(a * b) / gcd;
    }

    private String generateSolutionSteps(int a, int b) {
        StringBuilder steps = new StringBuilder();
        int q, r;
        do {
            q = a / b;
            r = a % b;
            steps.append(a).append(" = ").append(b).append("(").append(q).append(") + ").append(r).append("\n");
            a = b;
            b = r;
        } while (r != 0);

        return steps.toString();
    }



}