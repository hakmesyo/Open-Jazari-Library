package com.cezerilab.openjazarilibrary.device.ic_camera;


import java.awt.image.BufferedImage;


public class ICCamera extends InputDevice {
    private  BufferedImage image=null;
    // other needed variables

    @Override
    public BufferedImage getImage() {
        return image;
    }

    //other needed functions

}
