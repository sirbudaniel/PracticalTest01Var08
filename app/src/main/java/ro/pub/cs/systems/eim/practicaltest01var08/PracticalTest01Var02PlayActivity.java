package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {


    EditText riddle, answear;
    Button check, back;

    String ref = null;

    //  BROADCAST RECEIVER
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplication(), intent.getStringExtra("message"), Toast.LENGTH_LONG).show();
        }
    }

    private IntentFilter intentFilter = new IntentFilter();

    //    **************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        riddle = (EditText) findViewById(R.id.riddle);
        answear = (EditText) findViewById(R.id.answear);

        check = (Button) findViewById(R.id.check);
        back = (Button) findViewById(R.id.back);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras().containsKey("riddle")) {
            riddle.setText(intent.getStringExtra("riddle"));
        }
        if (intent != null && intent.getExtras().containsKey("answear")) {
            ref = intent.getStringExtra("answear");
        }

        //  SERVICE

        Intent service_intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        service_intent.putExtra("answear", ref);
        getApplicationContext().startService(service_intent);

        intentFilter.addAction("my_Intent");


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answear.getText().toString().compareTo(ref) == 0) {
                    Toast.makeText(getApplication(), "PERFECT", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), "TRY AGAIN", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0, null);
                finish();
            }
        });
    }

    //    BROADCAST RECEIVER
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
//  ******

    //  SERVICE
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
