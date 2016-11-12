package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.android;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import com.example.boles.cs313f16p4.R;
        import com.example.boles.cs313f16p4.UIListener;
        import com.example.boles.cs313f16p4.CounterModelFasade;
        import com.example.boles.cs313f16p4.ModelFacade;
        import com.example.boles.cs313f16p4.DefaultIAlarm;
        import com.example.boles.cs313f16p4.iAlarmModel;

/**
 * A thin adapter component for the csimple counter.
 *
 * @author laufer
 */
public class Adapter extends Activity implements UIUpdateListener {

    private /* static */ String TAG = "stopwatch-android-act1vity";

    /**
     * The state-based dynamic model.
     */
    private ModelFacade model;

    private iAlarmModel alerter;

    protected void setModel(final ModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alerter=new DefaultIAlarm(getApplicationContext());
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new CounterModelFasade());


        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    // TODO remaining lifecycle methods

    /**
     * Updates the counter the UI.
     * @param counter
     */
    public void updateTime(final int counter) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView tvCounter = (TextView) findViewById(R.id.counter);
            if (tvCounter!=null) {
                tvCounter.setText(Integer.toString(counter / 10) + Integer.toString(counter % 10));
            }
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    @Override
    public void Beep() {
        runOnUiThread(){
            try {
//// send the tone to the "alarm" stream (classic beeps go there) with 50% volume
//                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//                toneGen1.startTone(ToneGenerator.TONE_PROP_BEEP,150);

                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void RunAlert() {
        alerter.start();
    }

    @Override
    public void StopAlert() {
        alerter.stop();
    }


    public void onStartStopReset(final View view){
        final EditText counterEditor = (EditText) findViewById(R.id.initialCounter);
        String no=counterEditor.getText().toString();
        int startvalue=0;
        try {
            startvalue = Integer.valueOf(no);
        }catch (NumberFormatException e){

        }
        model.onStartStopReset(startvalue);
    }
    protected void saveModelToPrefs(Bundle savedInstanceState){
        if (savedInstanceState!=null){
            model.GetModelData(savedInstanceState);
        }
    }
    /** Attempts to read the externally saved counter value, model state and update the model.  */
    @SuppressLint("LongLogTag")
    protected void restoreModelFromPrefs(Bundle savedInstanceState) {
        Log.i(TAG, "restoring model from shared prefs");
        if (savedInstanceState!=null){
            model.RestoreModelData(savedInstanceState);
        }

    }

    /**
     * activity life sicle
     * @param outState
     */
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if (outState!=null) {
            final EditText counterEditor = (EditText) findViewById(R.id.initialCounter);
            String no = counterEditor.getText().toString();
            outState.putString("initialCounter",no);
            saveModelToPrefs(outState);
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null) {
            final EditText counterEditor = (EditText) findViewById(R.id.initialCounter);
            counterEditor.setText(savedInstanceState.getString("initialCounter",""));
            restoreModelFromPrefs(savedInstanceState);
        }

    }
    public void onDestroy(){
        super.onDestroy();
        model.onStart(); // stop playback if any
    }

//    protected void onStop(){
//        super.onStop();
//// stop alert if any. Just resrt a model.
////        model.onStart();
//
//    }
}
