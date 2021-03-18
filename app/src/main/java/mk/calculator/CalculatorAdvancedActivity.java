package mk.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

import mk.calculator.exceptions.IncorrectInputException;
import mk.calculator.parser.MathParser;


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
            String input = getInput(), output = getOutput();
            if (output.isEmpty() && !input.isEmpty()) {
                if (input.contains("%")) return;
                setInput(calculate(getInput()));
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
            setOutput(output + " " + input + " " + "=");
            setInput(calculate(getOutput()));
        });

        btn0.setOnClickListener(v -> {
            String input = getInput();
            if (!(input.length() == 1 && input.contains("0"))) {
                setInput(input + "0");
            }
        });

        btnComma.setOnClickListener(v -> {
            String input = getInput();
            if (input.length() == 0) {
                setInput("0.");
            } else if (input.length() == 1 && input.contains("-")) {
                setInput("-0.");
            } else if (!input.contains(".")) {
                setInput(input + ".");
            }
        });

        btnPlus.setOnClickListener(v -> {
            String input = getInput();
            String output = getOutput();

            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    setOutput(input + " " + "+");
                    setInput("");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    setOutput(output.subSequence(0, output.length() - 1) + " " + "+");
                }
            }

            if (input.length() != 0) {
                if (input.length() != 0) {
                    setOutput(output + " " + input + " " + "+");
                    setInput("");
                }
            }
        });

        btnMinus.setOnClickListener(v -> {
            String input = getInput();
            String output = getOutput();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    setInput("");
                    setOutput(input + " " + "-");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    setOutput(output.subSequence(0, output.length() - 1) + " " + "-");
                }
            }

            if (output.length() == 0 && input.length() == 0) {
                setInput("-");
            } else {
                if (input.length() != 0) {
                    setOutput(output + " " + input + " " + "-");
                    setInput("");
                }
            }
        });

        btnDivide.setOnClickListener(v -> {
            String input = getInput();
            String output = getOutput();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    setInput("");
                    setOutput(input + " " + "/");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    setOutput(output.subSequence(0, output.length() - 1) + " " + "/");
                }
            }

            if (input.length() != 0) {
                if (input.length() != 0) {
                    setOutput(output + " " + input + " " + "/");
                    setInput("");
                }
            }
        });

        btnMultiply.setOnClickListener(v -> {
            String input = getInput();
            String output = getOutput();
            if (input.length() != 0) {
                if (input.charAt(input.length() - 1) == '.')
                    return;
            }

            if (output.length() != 0) {
                if (output.charAt(output.length() - 1) == '=') {
                    setInput("");
                    setOutput(input + " " + "*");
                    return;
                }
            }

            if (input.length() == 0 && output.length() != 0) {
                if (output.charAt(output.length() - 1) == '-' || output.charAt(output.length() - 1) == '+' ||
                        output.charAt(output.length() - 1) == '*' || output.charAt(output.length() - 1) == '/') {
                    setOutput(output.subSequence(0, output.length() - 1) + " " + "*");
                }
            }

            if (input.length() != 0) {
                if (input.length() != 0) {
                    setOutput(output + " " + input + " " + "*");
                    setInput("");
                }
            }
        });


        btnPlusMinus.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(0) == '-') {
                    textViewInput.setText(textViewInput.getText().subSequence(1, textViewInput.getText().length()));
                } else {
                    setInput("-" + getInput().subSequence(0, getInput().length()));
                }
            }
        });
        btnAllClear.setOnClickListener(v -> {
            setInput("");
            setOutput("");
        });

        btnClear.setOnClickListener(v -> {
            if (getInput().contains("(") || getInput().contains("(")) {
                setInput("");
            }

            if (getInput().length() != 0) {
                setInput(getInput().subSequence(0, getInput().length() - 1).toString());
            }
        });
        // ****************************************************
        btnSin.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("sin(" + input + ")");
        });

        btnCos.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("cos(" + input + ")");
        });

        btnTan.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("tan(" + input + ")");
        });

        btnLn.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("ln(" + input + ")");
        });

        btnLog.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("log(" + input + ")");
        });

        btnXPower2.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.length() != 0) {
                input = String.valueOf(Math.pow(Double.parseDouble(input), 2));
            }
            setInput(input);
        });

        btnXPowerY.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            setInput(input + "^");
        });


        btnRoot.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty())
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput("√(" + input + ")");
        });


        btnPercent.setOnClickListener(v -> {
            String input = getInput();
            if (input.contains("(") || input.contains("^") || input.contains("√") || input.isEmpty() || input.contains("%"))
                return;
            if (input.contains("-") && input.length() != 0) {
                input = input.substring(1);
            }
            setInput(input + "%");
        });
    }

    private void setInput(String s) {
        textViewInput.setText(s);
    }

    private void setOutput(String s) {
        textViewOutput.setText(s);
    }

    private String getInput() {
        return textViewInput.getText().toString();
    }

    private String getOutput() {
        return textViewOutput.getText().toString();
    }

    public void displayToast(String msg) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show());
    }

    private String calculate(String n) {
        LinkedList<String> list = MathParser.parse(n);
        try {
            MathParser.validateExpression(list.listIterator());
            MathParser.calculateMathFunctions(list.listIterator());
            MathParser.calculatePercents(list.listIterator());
            MathParser.calculateMultiplicationDivison(list.listIterator());
            MathParser.calculateAddingSubstraction(list.listIterator());
            MathParser.calculatePow(list.listIterator());
        } catch (IncorrectInputException e) {
            displayToast(e.getMessage());
            setInput("");
            setOutput("");
            return "";
        }

        return list.get(0);
    }

    private void setButtonListener(Button button, String text) {
        button.setOnClickListener(v -> setInput(getInput() + text));
    }
}
