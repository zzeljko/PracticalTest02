package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by student on 24.05.2018.
 */

public class ServerThread extends Thread {

    private int port;
    private ServerSocket serverSocket;
    private int result;

    ServerThread(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ioException) {
            Log.e("bla", "An exception has occurred: " + ioException.getMessage());
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Log.i("bla", "[SERVER THREAD] Waiting for a client invocation...");
                Socket socket = serverSocket.accept();
                Log.i("bla", "[SERVER THREAD] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
                CommunicationThread communicationThread = new CommunicationThread(socket);
                communicationThread.start();
            }
        } catch (IOException ioException) {
            Log.e("bla", "[SERVER THREAD] An exception has occurred: " + ioException.getMessage());
        }
    }
}
