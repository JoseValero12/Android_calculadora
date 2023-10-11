package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder input = new StringBuilder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Configurar los listeners para los botones numéricos y operadores
        setNumericClickListener(R.id.btn0, "0");
        setNumericClickListener(R.id.btn1, "1");
        setNumericClickListener(R.id.btn2, "2");
        setNumericClickListener(R.id.btn3, "3");
        setNumericClickListener(R.id.btn4, "4");
        setNumericClickListener(R.id.btn5, "5");
        setNumericClickListener(R.id.btn6, "6");
        setNumericClickListener(R.id.btn7, "7");
        setNumericClickListener(R.id.btn8, "8");
        setNumericClickListener(R.id.btn9, "9");
        setOperatorClickListener(R.id.btnAdd, "+");
        setOperatorClickListener(R.id.btnSubtract, "-");
        setOperatorClickListener(R.id.btnMultiply, "*");
        setOperatorClickListener(R.id.btnDivide, "/");
        setOperatorClickListener(R.id.btnDecimal, ".");

        // Configurar el listener para el botón "C" (borrar)
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });

        // Configurar el listener para el botón "=" (igual)
        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcularResultado();
            }
        });
    }

    private void setNumericClickListener(int buttonId, final String value) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToInput(value);
            }
        });
    }

    private void setOperatorClickListener(int buttonId, final String operator) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToInput(" " + operator + " ");
            }
        });
    }

    private void appendToInput(String str) {
        input.append(str);
        resultTextView.setText(input.toString());
    }

    private void clearInput() {
        input.setLength(0);
        resultTextView.setText("");
    }

    private void CalcularResultado() {
        String expression = input.toString();
        try {
            // Evaluar la expresión y mostrar el resultado
            double result = evaluateExpression(expression);
            resultTextView.setText(String.valueOf(result));
        } catch (Exception e) {
            // Manejar errores en la expresión
            resultTextView.setText("Error");
        }
    }

    private double evaluateExpression(String expression) {
        // Implementar la lógica para evaluar la expresión aquí
        // Este es un ejemplo simple que maneja suma, resta, multiplicación y división
        // Utiliza un enfoque de evaluación de expresiones matemáticas más avanzado para casos más complejos.
        String[] parts = expression.split(" ");
        double result = Double.parseDouble(parts[0]);
        String operator = "";
        for (int i = 1; i < parts.length; i++) {
            if (i % 2 == 1) {
                operator = parts[i];
            } else {
                double operand = Double.parseDouble(parts[i]);
                if (operator.equals("+")) {
                    result += operand;
                } else if (operator.equals("-")) {
                    result -= operand;
                } else if (operator.equals("*")) {
                    result *= operand;
                } else if (operator.equals("/")) {
                    result /= operand;
                }
            }
        }
        return result;
    }
}

