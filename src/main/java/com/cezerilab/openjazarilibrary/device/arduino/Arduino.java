package com.cezerilab.openjazarilibrary.device.arduino;


import java.awt.image.BufferedImage;


public class Arduino extends InputDevice {
    private  BufferedImage image=null;
    // other needed variables

    @Override
    public BufferedImage getImage() {
        return image;
    }

    //other needed functions

}
