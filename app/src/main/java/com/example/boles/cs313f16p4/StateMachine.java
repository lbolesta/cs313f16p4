package com.example.boles.cs313f16p4;

        import android.os.Bundle;

        import com.example.boles.cs313f16p4.UIUpdateSource;
        import com.example.boles.cs313f16p4.UIListener;
        import com.example.boles.cs313f16p4.OnTickListener;

/**
 * The state machine for the state-based dynamic model of the simplecounter.
 * This interface is part of the State pattern.
 *
 * @author laufer
 */
public interface StateMachine extends UIListener, OnTickListener, UIUpdateSource, CounterSMStateView {
    public void RestoreModelData(Bundle res);
    public void GetModelData(Bundle res);
}

