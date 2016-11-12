package com.example.boles.cs313f16p4;


        import android.content.Context;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;

        import static java.lang.Thread.sleep;

/**
 * An implementation of the Alarm.
 */

public class DefaultIAlarm implements iAlarmModel {
    private volatile boolean active;
    private Ringtone r;

    public DefaultIAlarm(Context context){
/**
 * Wxit flag for the alert.
 */
        active=false;
        try {
// http://stackoverflow.com/questions/10335057/play-notification-default-sound-only-android
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            r = RingtoneManager.getRingtone(context, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Non-stop play the alert
     */
    @Override
    public void start() {
        active=true;
        while(active){ // Non-stop
            if (!r.isPlaying()) {
                r.play();
            }
            try {
                sleep(500);
//                r.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the alert playback
     */
    @Override
    public void stop() {
        active=false;
        r.stop();
    }
}

