package com.cezerilab.openjazarilibrary.device.kinect;


import java.awt.image.BufferedImage;


public class Kinect extends InputDevice {
    private  BufferedImage image=null;
    // other needed variables

    @Override
    public BufferedImage getImage() {
        return image;
    }

    //other needed functions

}
