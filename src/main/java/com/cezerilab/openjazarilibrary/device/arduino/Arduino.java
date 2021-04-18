package com.cezerilab.openjazarilibrary.device.arduino;


import java.awt.image.BufferedImage;


public class Arduino implements InputDevice{
    private  BufferedImage image=null;
    // other needed variables

    @Override
    public BufferedImage getImage() {
        return image;
    }

    //other needed functions

}
