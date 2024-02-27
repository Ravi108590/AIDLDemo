package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class sAdditionService extends Service {
    public sAdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
 AdditionService.Stub mBinder = new AdditionService.Stub() {
        //Aidl function reflects here if everything's implemented wright
     @Override
     public int add(int a, int b) throws RemoteException {
         return a + b;
     }
 };

}