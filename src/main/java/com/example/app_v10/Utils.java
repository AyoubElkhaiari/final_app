package com.example.app_v10;

import android.bluetooth.BluetoothSocket;

public class Utils {
    private BluetoothSocket bluetoothSocket=null;
    private static boolean connected = false;
    private static Utils instance=null;
    private Utils(){

    }
    public static Utils getInstance(){
        if(instance == null){
            instance = new Utils();
        }
        return instance;
    }

    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }

    public void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
        this.bluetoothSocket = bluetoothSocket;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
