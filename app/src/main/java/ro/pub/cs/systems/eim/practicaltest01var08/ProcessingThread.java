package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.sql.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private double arithmeticMean;
    private double geometricMean;
    Random rand = new Random();
    String message = "";


    public ProcessingThread(Context context,String answear) {
        this.context = context;


        int randomNum = rand.nextInt(answear.length());

        for (int i  = 0; i < answear.length(); i++) {
            if (i == randomNum) {
                message += answear.charAt(i);
            } else {
                message += "*";
            }
        }
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("my_Intent");
        intent.putExtra("message", message);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}
