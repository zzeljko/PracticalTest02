package ro.pub.cs.systems.eim.practicaltest02;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by student on 24.05.2018.
 */

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String operation;
    private String op1;
    private String op2;
    private Context context;

    ClientThread(String address, int port, String operation, String op1, String op2, Context context) {
        this.address = address;
        this.port = port;
        this.operation = operation;
        this.op1 = op1;
        this.op2 = op2;
        this.context = context;
    }

    @Override
    public void run() {

        try {
            Socket socket = new Socket(address, port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String command = operation + "," + op1 + "," + op2 + "\n";
            printWriter.println(command);
            printWriter.flush();

            final String result = bufferedReader.readLine();
            Log.d("result", result);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                        public void run() {
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
