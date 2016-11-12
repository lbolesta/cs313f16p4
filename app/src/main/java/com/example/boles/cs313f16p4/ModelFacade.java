package com.example.boles.cs313f16p4;

import android.os.Bundle;

import com.example.boles.cs313f16p4.UIUpdateSource;
import com.example.boles.cs313f16p4.UIListener;


/**
 * A thin model facade. Following the Facade pattern,
 * this isolates the complexity of the model from its clients (usually the adapter).
 */
public interface ModelFacade extends UIListener, UIUpdateSource {
    void onStart();
    public void RestoreModelData(Bundle res);
    public void GetModelData(Bundle res);
}
