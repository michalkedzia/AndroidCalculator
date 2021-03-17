package mk.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CalculatorAdvancedActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnComma, btnEqual, btnPlus, btnMinus,
            btnDivide, btnMultiply, btnPlusMinus, btnClear, btnAllClear,
    btnSin,btnCos,btnTan,btnLn,btnLog,btnRoot,btnXPower2,btnXPowerY;
    private TextView textViewInput, textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_advanced_activity);
        initializeViews();
        initializeListeners();

        if (savedInstanceState != null) {
            textViewInput.setText(savedInstanceState.getString("input"));
            textViewOutput.setText(savedInstanceState.getString("output"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("input", textViewInput.getText().toString());
        outState.putString("output", textViewOutput.getText().toString());
    }

    private void initializeViews() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnComma = findViewById(R.id.btnComma);
        btnEqual = findViewById(R.id.btnEqual);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnClear = findViewById(R.id.btnClear);
        btnAllClear = findViewById(R.id.btnClearAll);
        textViewInput = findViewById(R.id.textViewInput);
        textViewOutput = findViewById(R.id.textViewOutput);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnLn = findViewById(R.id.btnLn);
        btnLog = findViewById(R.id.btnLog);
        btnRoot = findViewById(R.id.btnRoot);
        btnXPower2 = findViewById(R.id.btnPowerTwo);
        btnXPowerY = findViewById(R.id.btnPowerY);
    }

    void initializeListeners() {
        setButtonListener(btn1, "1");
        setButtonListener(btn2, "2");
        setButtonListener(btn3, "3");
        setButtonListener(btn4, "4");
        setButtonListener(btn5, "5");
        setButtonListener(btn6, "6");
        setButtonListener(btn7, "7");
        setButtonListener(btn8, "8");
        setButtonListener(btn9, "9");
        btnEqual.setOnClickListener(v -> {
            String input = textViewInput.getText().toString(), output = textViewOutput.getText().toString();
            if (output.length() == 0) return;
            if (output.charAt(output.length() - 1) == '=') return;

            if (output.charAt(output.length() - 1) == '+' || output.charAt(output.length() - 1) == '-' ||
                    output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                if (input.length() == 0) {
                    output = output.subSequence(0, output.length() - 1).toString();
                }
            }

            textViewOutput.setText(output + input + "=");
            textViewInput.setText(calculate(textViewOutput.getText().toString()));
        });

        btn0.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (!(input.length() == 1 && input.contains("0"))) {
                textViewInput.setText(input + "0");
            }
        });

        btnComma.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.length() == 0) {
                textViewInput.setText("0.");
            } else if (input.length() == 1 && input.contains("-")) {
                textViewInput.setText("-0.");
            } else if (!input.contains(".")) {
                textViewInput.setText(textViewInput.getText() + ".");
            }
        });

        btnPlus.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            String output = textViewOutput.getText().toString();

            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    textViewOutput.setText(input + "+");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + "+");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + input + "+");
                    textViewInput.setText("");
                }
            }
        });

        btnMinus.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            String output = textViewOutput.getText().toString();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    textViewOutput.setText(input + "-");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + "-");
                }
            }

            if (input.length() == 1 && input.contains("-")) {
                return;
            } else if (output.length() == 0 && input.length() == 0) {
                textViewInput.setText("-");
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + input + "-");
                    textViewInput.setText("");
                }
            }
        });

        btnDivide.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            String output = textViewOutput.getText().toString();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    textViewOutput.setText(input + "/");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + "/");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + input + "/");
                    textViewInput.setText("");
                }
            }
        });

        btnMultiply.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            String output = textViewOutput.getText().toString();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    textViewOutput.setText(input + "*");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + "*");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + input + "*");
                    textViewInput.setText("");
                }
            }
        });


        btnPlusMinus.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(0) == '-') {
                    textViewInput.setText(textViewInput.getText().subSequence(1, textViewInput.getText().length()));
                } else {
                    textViewInput.setText("-" + textViewInput.getText().subSequence(0, textViewInput.getText().length()));
                }
            }
        });
        btnAllClear.setOnClickListener(v -> {
            textViewInput.setText("");
            textViewOutput.setText("");
        });

        btnClear.setOnClickListener(v -> {
            if (textViewInput.length() != 0) {
                textViewInput.setText(textViewInput.getText().subSequence(0, textViewInput.getText().length() - 1));
            }
        });
        // ****************************************************
        btnSin.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.contains("-") && input.length()!=0){
                input = input.substring(1);
            }
            textViewInput.setText("sin("+ input + ")");
        });

        btnCos.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.contains("-") && input.length()!=0){
                input = input.substring(1);
            }
            textViewInput.setText("cos("+ input + ")");
        });

        btnTan.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.contains("-") && input.length()!=0){
                input = input.substring(1);
            }
            textViewInput.setText("tan("+ input + ")");
        });

        btnLn.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.contains("-") && input.length()!=0){
                input = input.substring(1);
            }
            textViewInput.setText("ln("+ input + ")");
        });

        btnLog.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.contains("-") && input.length()!=0){
                input = input.substring(1);
            }
            textViewInput.setText("log("+ input + ")");
        });

        btnXPower2.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            if(input.length()!=0){
                input = String.valueOf(Math.pow(Double.parseDouble(input),2));
            }
            textViewInput.setText(input);
        });

        btnXPowerY.setOnClickListener(v->{
            String input = textViewInput.getText().toString();
            if(input.contains("(") || input.contains("^") || input.isEmpty()) return;
            textViewInput.setText( input + "^");
        });
    }

    private String calculate(String n) {
        String[] array = n.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");
        List<String> list = new ArrayList<String>();

        if (array[0].equals("-")) {
            array[1] = "-" + array[1];
            list = Arrays.asList(array);
            list = new LinkedList<>(list.subList(1, list.size()));
        } else {
            list = new LinkedList<>(Arrays.asList(array));
        }

        ListIterator<String> iterator = list.listIterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals("--")) iterator.set("+");
            if (iterator.equals("+") || iterator.equals("/") || iterator.equals("*") || iterator.equals("-")) {
                if (!iterator.hasNext()) {
                    iterator.remove();
                }
            }

        }

        iterator = list.listIterator();

        while (iterator.hasNext()) {
            String op = iterator.next();
            if (op.equals("*")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a * b));
            } else if (op.equals("/")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                String check = String.valueOf(a / b);
                if (check.equals("Infinity")) {
                    textViewOutput.setText("");
                    textViewOutput.setText("");
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    return "";
                }
                iterator.set(String.valueOf(a / b));
            }
        }

        iterator = list.listIterator();
        while (iterator.hasNext()) {
            String op = iterator.next();
            if (op.equals("+")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a + b));
            } else if (op.equals("-")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a - b));
            }
        }

        return list.get(0);
    }

    private void setButtonListener(Button button, String text) {
        button.setOnClickListener(v -> textViewInput.setText(textViewInput.getText().toString() + text));
    }


}
