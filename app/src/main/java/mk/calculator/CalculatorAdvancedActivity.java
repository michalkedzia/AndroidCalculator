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
import java.util.StringTokenizer;

public class CalculatorAdvancedActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnComma, btnEqual, btnPlus, btnMinus,
            btnDivide, btnMultiply, btnPlusMinus, btnClear, btnAllClear,
            btnSin, btnCos, btnTan, btnLn, btnLog, btnRoot, btnXPower2, btnXPowerY, btnPercent;
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
        btnPercent = findViewById(R.id.btnPercent);
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
            if (output.isEmpty() && !input.isEmpty()) {

                if (input.contains("%")) return;

                textViewInput.setText(calculate(textViewInput.getText().toString()));
                return;
            }


            if (output.length() == 0) return;
            if (output.charAt(output.length() - 1) == '=') return;

            if (output.charAt(output.length() - 1) == '+' || output.charAt(output.length() - 1) == '-' ||
                    output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                if (input.length() == 0) {
                    output = output.subSequence(0, output.length() - 1).toString();
                }
            }

            textViewOutput.setText(output + " " + input + " " + "=");
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
                    textViewOutput.setText(input + " " + "+");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + " " + "+");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + " " + input + " " + "+");
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
                    textViewOutput.setText(input + " " + "-");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + " " + "-");
                }
            }

            if (input.length() == 1 && input.contains("-")) {
                return;
            } else if (output.length() == 0 && input.length() == 0) {
                textViewInput.setText("-");
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + " " + input + " " + "-");
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
                    textViewOutput.setText(input + " " + "/");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + " " + "/");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + " " + input + " " + "/");
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
                    textViewOutput.setText(input + " " + "*");
                    textViewInput.setText("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    textViewOutput.setText(output.subSequence(0, output.length() - 1) + " " + "*");
                }
            }

            if (input.length() == 0) {
                return;
            } else {
                if (input.length() != 0) {
                    textViewOutput.setText(output + " " + input + " " + "*");
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
            if (textViewInput.getText().toString().contains("(") || textViewInput.getText().toString().contains("(")) {
                textViewInput.setText("");
            }

            if (textViewInput.length() != 0) {
                textViewInput.setText(textViewInput.getText().subSequence(0, textViewInput.getText().length() - 1));
            }
        });
        // ****************************************************
        btnSin.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("sin(" + input + ")");
        });

        btnCos.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("cos(" + input + ")");
        });

        btnTan.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("tan(" + input + ")");
        });

        btnLn.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("ln(" + input + ")");
        });

        btnLog.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("log(" + input + ")");
        });

        btnXPower2.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.length() != 0) {
                input = String.valueOf(Math.pow(Double.parseDouble(input), 2));
            }
            textViewInput.setText(input);
        });

        btnXPowerY.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            textViewInput.setText(input + "^");
        });


        btnRoot.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText("√(" + input + ")");
        });


        btnPercent.setOnClickListener(v -> {
            String input = textViewInput.getText().toString();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty() || input.contains("%"))
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            textViewInput.setText(input + "%");
        });


    }

    public boolean isValidNumber(String string) {
        try {
            Double.valueOf(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void displayToast(String msg){
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show());
    }


    public static LinkedList<String> parse(String s) {
        StringTokenizer st = new StringTokenizer(s, " ^", true);
        LinkedList<String> list = new LinkedList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!token.equals(" ") && !token.equals("=")) {
                list.add(token);
            }
        }
        return list;
    }

    private String calculate(String n) {
        LinkedList<String> list = parse(n);
        ListIterator<String> iterator = list.listIterator();
//        System.out.println(list);

        iterator = list.listIterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
//            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/") && !s.equals("^") && !s.contains("%")){
//                try {
//                    Double.valueOf(s);
//                }catch (Exception e){
//                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
//                    return "";
//                }
//            }

            if (s.contains("%")) {
                if (s.charAt(s.length() - 1) != '%') {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
                    return "";
                }
            }

            if (s.equals("^")) {
                if (!iterator.hasNext()) {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
                    return "";
                } else {
                    try {
                        Double.valueOf(iterator.next());
                    } catch (Exception e) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
                        return "";
                    }
                    iterator.previous();
                }

            }

        }


        iterator = list.listIterator();

        while (iterator.hasNext()) {
            double sign = 1.0;
            int offset = 0;
            String s = iterator.next();
            if (s.contains("-")){
                sign = -1.0;
                offset = 1;
            }

            if (s.contains("sin")) {
                if(!isValidNumber(s.substring(4 + offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.sin(Double.parseDouble(s.substring(4+ offset, s.length() - 1)))));
            } else if (s.contains("cos")) {
                if(!isValidNumber(s.substring(4+ offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.cos(Double.parseDouble(s.substring(4+ offset, s.length() - 1)))));
            } else if (s.contains("tan")) {
                if(!isValidNumber(s.substring(4+ offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.tan(Double.parseDouble(s.substring(4+ offset, s.length() - 1)))));
            } else if (s.contains("ln")) {
                if(!isValidNumber(s.substring(3+ offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.log10(Double.parseDouble(s.substring(3+ offset, s.length() - 1)))));
            } else if (s.contains("log")) {
                if(!isValidNumber(s.substring(4+ offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.log(Double.parseDouble(s.substring(4+ offset, s.length() - 1)))));
            } else if (s.contains("√")) {
                if(!isValidNumber(s.substring(2+ offset, s.length() - 1))){
                    displayToast("Błędne dane wejsciowe");
                    return "";
                }
                iterator.set(String.valueOf(sign * Math.log(Double.parseDouble(s.substring(2+ offset, s.length() - 1)))));
            }
        }


        iterator = list.listIterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.contains("%")) {
                String percent = s.substring(0, s.length() - 1);
                iterator.previous();
                String sign = iterator.previous();
                String a = iterator.previous();
                iterator.next();
                iterator.next();
                iterator.next();

                if (sign.equals("*") || sign.equals("/")) {
                    iterator.set(String.valueOf(Double.parseDouble(percent) / 100.0));
                } else {
                    iterator.set(String.valueOf(Double.parseDouble(a) * (Double.parseDouble(percent) / 100.0)));
                }
            }
        }


        iterator = list.listIterator();


        if (iterator.hasNext()) {
            if (iterator.next().equals("-")) {
                iterator.remove();
                String s = iterator.next();
                iterator.set("-" + s);

            }
        }


        iterator = list.listIterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("^")) {
                s = iterator.next();
                if (s.equals("-")) {
                    iterator.remove();
                    s = iterator.next();
                    iterator.set("-" + s);
                    iterator.previous();
                } else iterator.previous();
                iterator.previous();
                iterator.previous();


                if (iterator.hasPrevious()) {
                    s = iterator.previous();
                    if (s.equals("-")) {
                        if (iterator.hasPrevious()) {
                            if (iterator.previous().equals("-")) {
                                iterator.next();
                                iterator.remove();
                                iterator.next();
                                s = iterator.next();
                                iterator.set("-" + s);
                            } else {
                                iterator.next();
                                iterator.next();
                            }
                        } else iterator.next();
                    } else iterator.next();
                }

                iterator.next();
                iterator.next();
            }
        }


        iterator = list.listIterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("^")) {
                iterator.previous();
                String a = iterator.previous();
                iterator.remove();
                iterator.next();
                iterator.remove();
                String b = iterator.next();
                iterator.set(String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b))));
            }
        }


        iterator = list.listIterator();
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
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
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
        System.out.println(list);
        return list.get(0);

    }

    private void setButtonListener(Button button, String text) {
        button.setOnClickListener(v -> textViewInput.setText(textViewInput.getText().toString() + text));
    }


}
