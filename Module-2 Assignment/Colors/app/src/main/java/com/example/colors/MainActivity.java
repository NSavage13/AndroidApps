package com.example.colors;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout redChannelLayout, greenChannelLayout, blueChannelLayout;
    private TextInputEditText redChannelEditText, greenChannelEditText, blueChannelEditText;
    private Button submitButton;
    private View colorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redChannelLayout = findViewById(R.id.textInputLayoutRedChannel);
        greenChannelLayout = findViewById(R.id.textInputLayoutGreenChannel);
        blueChannelLayout = findViewById(R.id.textInputLayoutBlueChannel);

        redChannelEditText = findViewById(R.id.textInputEditTextRedChannel);
        greenChannelEditText = findViewById(R.id.textInputEditTextGreenChannel);
        blueChannelEditText = findViewById(R.id.textInputEditTextBlueChannel);

        submitButton = findViewById(R.id.btn_submit);
        colorView = findViewById(R.id.color_view);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redString = redChannelEditText.getText().toString();
                String greenString = greenChannelEditText.getText().toString();
                String blueString = blueChannelEditText.getText().toString();

                int red = parseHexString(redString);
                int green = parseHexString(greenString);
                int blue = parseHexString(blueString);

                int color = Color.rgb(red, green, blue);
                colorView.setBackgroundColor(color);
            }
        });

        redChannelEditText.addTextChangedListener(new ColorTextWatcher(redChannelLayout));
        greenChannelEditText.addTextChangedListener(new ColorTextWatcher(greenChannelLayout));
        blueChannelEditText.addTextChangedListener(new ColorTextWatcher(blueChannelLayout));
    }

    private int parseHexString(String hexString) {
        int hexValue = 0;
        try {
            hexValue = Integer.parseInt(hexString, 16);
        } catch (NumberFormatException e) {
            // Handle invalid hex string
        }
        return hexValue;
    }

    private class ColorTextWatcher implements TextWatcher {
        private TextInputLayout layout;

        public ColorTextWatcher(TextInputLayout layout) {
            this.layout = layout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 2) {
                int hexValue = parseHexString(s.toString());
                if (hexValue < 0 || hexValue > 255) {
                    layout.setError("Invalid hex value");
                } else {
                    layout.setError(null);
                }
            } else {
                layout.setError("Must be 2 characters");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
