package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MYTAG", "service");
        String answear = intent.getStringExtra("answear");
        processingThread = new ProcessingThread(this, answear);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
