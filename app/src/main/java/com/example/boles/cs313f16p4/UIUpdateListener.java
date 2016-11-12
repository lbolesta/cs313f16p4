package com.example.boles.cs313f16p4;


/**
 * UI adapter responsibility to schedule incoming events on UI thread
 * A listener for UI update notifications.
 * This interface is typically implemented by the adapter, with the
 * notifications coming from the model.
 */
public interface UIUpdateListener {
    /**
     * Update the counter field with the timeValue
     * @param timeValue
     */
    void updateTime(int timeValue);

    /**
     * Update the current machine state field with the stateId
     * @param stateId
     */
    void updateState(int stateId);

    /**
     * Plays short sound
     */
    void Beep();
    /**
     * Plays non-stop sound
     */
    void RunAlert();
    /**
     * Stops non-stop sound
     */
    void StopAlert();
}

