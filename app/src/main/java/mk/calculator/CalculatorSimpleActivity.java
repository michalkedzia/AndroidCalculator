package mk.calculator;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculatorSimpleActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnComma, btnEqual, btnPlus, btnMinus,
            btnDivide, btnMultiply, btnPlusMinus, btnClear, btnAllClear;
    private TextView textViewInput, textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_simple_activity);
        initializeViews();
        if (savedInstanceState != null) {
            textViewInput.setText(savedInstanceState.getString("input", ""));
        }
        initializeListeners();


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outPersistentState.putString("input", textViewInput.getText().toString());
        super.onSaveInstanceState(outState, outPersistentState);
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
        setButtonListener(btnEqual, "="); // TODO wynik po rowna sie

        btn0.setOnClickListener(v -> {
            if (!(textViewInput.length() == 1 && textViewInput.getText().toString().contains("0"))) {
                if (!(textViewInput.length() == 2 && textViewInput.getText().charAt(0) == '-'))
                    textViewInput.setText(textViewInput.getText().toString() + "0");
            }
        });

        btnComma.setOnClickListener(v -> {
            if (textViewInput.length() == 0) {
                textViewInput.setText("0.");
            } else if (textViewInput.length() == 1 && textViewInput.getText().toString().contains("-")) {
                textViewInput.setText("-0.");
            } else if (!textViewInput.getText().toString().contains(".")) {
                textViewInput.setText(textViewInput.getText() + ".");
            }
        });

        btnPlus.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(textViewInput.getText().length() - 1) == '.')
                    return;
            }

            if (textViewInput.length() == 0) {
                return;
            } else {
                if (textViewInput.length() != 0) {
                    textViewOutput.setText(textViewOutput.getText() + textViewInput.getText().toString() + "+");
                    textViewInput.setText("");
                }
            }
        });

        btnMinus.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(textViewInput.getText().length() - 1) == '.')
                    return;
            }
            if (textViewInput.length() == 1 && textViewInput.getText().toString().contains("-")) {
                return;
            } else if (textViewOutput.getText().length() == 0 && textViewInput.length() == 0) {
                textViewInput.setText("-");
            } else {
                if (textViewInput.length() != 0) {
                    textViewOutput.setText(textViewOutput.getText() + textViewInput.getText().toString() + "-");
                    textViewInput.setText("");
                }
            }
        });

        btnDivide.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(textViewInput.getText().length() - 1) == '.')
                    return;
            }
            if (textViewInput.length() == 0) {
                return;
            } else {
                if (textViewInput.length() != 0) {
                    textViewOutput.setText(textViewOutput.getText() + textViewInput.getText().toString() + "/");
                    textViewInput.setText("");
                }
            }
        });

        btnMultiply.setOnClickListener(v -> {
            if (textViewInput.getText().length() != 0) {
                if (textViewInput.getText().charAt(textViewInput.getText().length() - 1) == '.')
                    return;
            }
            if (textViewInput.length() == 0) {
                return;
            } else {
                if (textViewInput.length() != 0) {
                    textViewOutput.setText(textViewOutput.getText() + textViewInput.getText().toString() + "*");
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
    }

    private String calculate(String n){
        String[] array = n.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");
        List<String> list = new ArrayList<String>();

        if(array[0].equals("-")){
            array[1]= "-" + array[1];
            list = Arrays.asList(array);
            list = new LinkedList<>(list.subList(1, list.size()));
        }

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            String op = iterator.next();
            if(op.equals("*")){
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a*b));
            }else if(op.equals("/")){
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a/b));
            }
        }

        iterator = list.listIterator();
        while (iterator.hasNext()){
            String op = iterator.next();
            if(op.equals("+")){
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a+b));
            }else if(op.equals("-")){
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a-b));
            }
        }
        return list.get(0);
    }

    private void setButtonListener(Button button, String text) {
        button.setOnClickListener(v -> textViewInput.setText(textViewInput.getText().toString() + text));
    }
}
