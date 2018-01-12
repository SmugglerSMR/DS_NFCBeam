package com.deeepsense.smuggler.ds_nfcbeam.feature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.ndeftools.Message;
import org.ndeftools.Record;
import org.ndeftools.util.activity.NfcReaderActivity;

public class BeamReaderActivity extends NfcReaderActivity {
    private static final String ACTION = "com.deeepsense.smuggler.ds_nfcbeam.feature.READ_TAG";
    private static final String TAG = BeamReaderActivity.class.getName();

    protected Message message;


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, BeamReaderActivity.class);
        intent.setAction(ACTION);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam_reader);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setDetecting(true);

        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onNfcStateEnabled() {
        Toast.makeText(getApplicationContext(), R.string.nfcAvailableEnabled,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNfcStateDisabled() {
        Toast.makeText(getApplicationContext(), R.string.nfcAvailableDisabled,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNfcStateChange(boolean enabled) {
        if(enabled)
            Toast.makeText(getApplicationContext(), R.string.nfcTurnOn,
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), R.string.nfcTurnOff,
                    Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNfcFeatureNotFound() {
        Toast.makeText(getApplicationContext(), R.string.noNFC,
                Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onTagLost() {
        Toast.makeText(getApplicationContext(), R.string.NFCtagLost,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void readNdefMessage(Message message) {
        if(message.size() > 1)
            Toast.makeText(getApplicationContext(), R.string.readMultipleRecordNDEFMessage,
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), R.string.readSingleRecordNDEFMessage,
                    Toast.LENGTH_LONG).show();

        this.message = message;

        for(int i=0; i < message.size(); i++) {
            Record record = message.get(i);

            Log.d(TAG, "Record " + i + " type "
                    + record.getClass().getSimpleName());
//            // your own code here, for example:
//            if (record instanceof MimeRecord) {
//                // ..
//            } else if (record instanceof ExternalTypeRecord) {
//                // ..
//            } else if (record instanceof TextRecord) {
//                // ..
//            } else { // more else
//                // ..
//            }

        }
        setResult(Activity.RESULT_OK,
                BeamReaderFragment.getIntentForResult(message));
        finish();
    }

    @Override
    protected void readEmptyNdefMessage() {
        Toast.makeText(getApplicationContext(), R.string.readEmptyMessage,
                Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    protected void readNonNdefMessage() {
        Toast.makeText(getApplicationContext(), R.string.readNonNDEFMessage,
                Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
