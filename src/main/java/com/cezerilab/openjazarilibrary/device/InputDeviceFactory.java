package com.cezerilab.openjazarilibrary.device;

public class InputDeviceFactory{
    public InputDevice getInputDeviceName(String deviceName){
        if(deviceName == null){
            return null;
        }else if (deviceName.equalsIgnoreCase("WEBCAM")){
            return new Camera();
        }else if (deviceName.equalsIgnoreCase("IC_CAMERA")){
            return new IC_CAMERA();
        }else if (deviceName.equalsIgnoreCase("ARDUINO")){
            return new Arduino();
        }else{
            return null;
        }
    }
}