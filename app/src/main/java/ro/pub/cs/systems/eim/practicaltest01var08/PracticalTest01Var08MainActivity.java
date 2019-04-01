package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {


    EditText riddle, answear;
    Button play;
    final int REQUESTCODE = 22;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.play && riddle.getText().toString().length() > 0 && answear.getText().length() > 0) {
                Log.d("MYTAG", "heheh");
//  SECOND ACTIVITY
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
//                intent.setAction("ro.pub.cs.systems.eim.practicaltest01var08.intent.action.play");
                intent.putExtra("riddle", riddle.getText().toString());
                intent.putExtra("answear", answear.getText().toString());
                startActivityForResult(intent, REQUESTCODE);
            }



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddle = (EditText) findViewById(R.id.riddle);
        answear = (EditText) findViewById(R.id.answear);
        play = (Button) findViewById(R.id.play);

        //  SAVE STATE
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("first")) {
                riddle.setText(savedInstanceState.getString("riddle"));
            }
            if (savedInstanceState.containsKey("second")) {
                answear.setText(savedInstanceState.getString("answear"));
            }
        }

        play.setOnClickListener(buttonClickListener);
    }




    //  SECOND ACTIVITY
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case REQUESTCODE:
                if (resultCode == 0) {
                    Toast.makeText(getApplication(), "OK", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //  SAVE STATE
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("riddle", riddle.getText().toString());
        savedInstanceState.putString("answear", answear.getText().toString());
    }
}
