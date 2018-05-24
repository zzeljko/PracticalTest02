package ro.pub.cs.systems.eim.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    private EditText serverPortEditText;
    private EditText clientPortEditText;
    private EditText clientAddressEditText;
    private ServerThread serverThread;
    private Button serverStartButton;
    private ClientThread clientThread;
    private EditText clientOp1EditText;
    private EditText clientOp2EditText;
    private Button clientAddButton;
    private Button clientMulButton;

    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e("bla", "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }

    }

    private class AddButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientPort = clientPortEditText.getText().toString();
            String clientAddress = clientAddressEditText.getText().toString();
            String op1 = clientOp1EditText.getText().toString();
            String op2 = clientOp2EditText.getText().toString();
            if (clientPort == null || clientPort.isEmpty() || clientAddress == null || clientAddress.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            clientThread = new ClientThread(clientAddress, Integer.parseInt(clientPort), "add", op1, op2, getApplicationContext());
            clientThread.start();
        }

    }

    private class MulButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientPort = clientPortEditText.getText().toString();
            String clientAddress = clientAddressEditText.getText().toString();
            String op1 = clientOp1EditText.getText().toString();
            String op2 = clientOp2EditText.getText().toString();
            if (clientPort == null || clientPort.isEmpty() || clientAddress == null || clientAddress.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            clientThread = new ClientThread(clientAddress, Integer.parseInt(clientPort), "mul", op1, op2, getApplicationContext());
            clientThread.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        serverPortEditText = findViewById(R.id.server_port_edit_text);
        serverStartButton = findViewById(R.id.connect_button);
        serverStartButton.setOnClickListener(new ConnectButtonClickListener());
        clientAddButton = findViewById(R.id.get_result_add_button);
        clientMulButton = findViewById(R.id.get_result_mul_button);
        clientAddButton.setOnClickListener(new AddButtonClickListener());
        clientMulButton.setOnClickListener(new MulButtonClickListener());
        clientPortEditText = findViewById(R.id.client_port_edit_text);
        clientAddressEditText = findViewById(R.id.client_address_edit_text);
        clientOp1EditText = findViewById(R.id.op1_edit_text);
        clientOp2EditText = findViewById(R.id.op2_edit_text);
    }
}
