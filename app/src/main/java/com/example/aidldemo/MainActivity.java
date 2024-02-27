package com.example.aidldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import com.example.aidldemo.databinding.ActivityMainBinding;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    AdditionService mServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        Intent intent = new Intent(this, sAdditionService.class);
        bindService(intent, mserviceConnection, Context.BIND_AUTO_CREATE);
    }

    // creating the service
    ServiceConnection mserviceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServices = AdditionService.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void add(View view)
    {
        // converting the data into the integer
        int numf = Integer.parseInt(activityMainBinding.numfirst.getText().toString());
        int nums = Integer.parseInt(activityMainBinding.numsecond.getText().toString());

        int result = 0;
        try {
            result = mServices.add(numf, nums);
        }catch (RemoteException e)
        {
            e.printStackTrace();
        }

        activityMainBinding.output.setText(""+result);
    }
}