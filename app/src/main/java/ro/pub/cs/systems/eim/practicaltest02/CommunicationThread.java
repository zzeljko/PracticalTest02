package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by student on 24.05.2018.
 */

public class CommunicationThread extends Thread {

    private Socket socket;

    CommunicationThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bufferedReader == null || printWriter == null) {
            Log.e("bla", "[COMMUNICATION THREAD] Buffered Reader / Print Writer are null!");
            return;
        }
        String command = "";
        try {
            command = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] el = command.split(",");
        int result;
        if (el[0].equals("add"))
            result = Integer.parseInt(el[1]) + Integer.parseInt(el[2]);
        else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = Integer.parseInt(el[1]) * Integer.parseInt(el[2]);
        }

        printWriter.println(result);
        printWriter.flush();

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
