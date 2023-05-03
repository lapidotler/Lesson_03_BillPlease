package sg.edu.rp.c346.id22024044.lesson_03_billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Step 1: Declare field variables
    TextView amtDisplay;
    EditText amtInput;
    TextView paxDisplay;
    EditText paxInput;

    ToggleButton tbtnSVS;
    ToggleButton tbtnGST;

    TextView discDisplay;
    EditText discInput;

    RadioGroup rgPayment;

    Button btnSplit;
    Button btnReset;

    TextView totalBillDisplay;
    TextView eachPayDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link field variables to UI components in layout
        amtDisplay = findViewById(R.id.viewAmount);
        amtInput = findViewById(R.id.editAmount);
        paxDisplay = findViewById(R.id.viewPax);
        paxInput = findViewById(R.id.editPax);

        tbtnSVS = findViewById(R.id.toggleButtonSVS);
        tbtnGST = findViewById(R.id.toggleButtonGST);

        discDisplay = findViewById(R.id.viewDiscount);
        discInput = findViewById(R.id.editDiscount);

        rgPayment = findViewById(R.id.radioGroupPayment);

        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);

        totalBillDisplay = findViewById(R.id.viewTotalBill);
        eachPayDisplay = findViewById(R.id.viewEachPay);


        btnSplit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String totalBillResponse;
                String eachPayResponse;
                int checkedRadioId = rgPayment.getCheckedRadioButtonId();

                int totalAmount = Integer.parseInt(amtInput.getText().toString());
                int totalPax = Integer.parseInt(paxInput.getText().toString());
                int discNum = Integer.parseInt(discInput.getText().toString());

                double discAmt = totalAmount * discNum / 100;
                double totalBill = totalAmount - discAmt;
                double finalTotalBill = 0;

                if(tbtnSVS.isChecked() && tbtnGST.isChecked()) {
                    totalBill = totalBill + (totalBill * 0.07 * 0.10);

                } else if (tbtnSVS.isChecked()) {
                    totalBill = totalBill + (totalBill * 0.10);

                } else if (tbtnGST.isChecked()) {
                    totalBill = totalBill + (totalBill * 0.07);

                }

                finalTotalBill = Double.parseDouble(String.format("%.2f", totalBill));
                double eachBill = (finalTotalBill) / totalPax;
                double finalEachBill = Double.parseDouble(String.format("%.2f", eachBill));;

                if(checkedRadioId == R.id.radioButtonCash){
                    eachPayResponse = "Each Pays: $" + finalEachBill + " in cash.";

                } else {
                    eachPayResponse = "Each Pays: $" + finalEachBill + " via PayNow \nto 9123 4567.";

                }
                totalBillResponse = "Total Bill: $" + finalTotalBill;

                totalBillDisplay.setText(totalBillResponse);
                eachPayDisplay.setText(eachPayResponse);

            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtInput.setText(null);
                paxInput.setText(null);
                discInput.setText(null);

                tbtnGST.setChecked(false);
                tbtnSVS.setChecked(false);

                rgPayment.clearCheck();

                totalBillDisplay.setText("Total Bill: $");
                eachPayDisplay.setText("Each Pays: $");

            }
        });

    }
}