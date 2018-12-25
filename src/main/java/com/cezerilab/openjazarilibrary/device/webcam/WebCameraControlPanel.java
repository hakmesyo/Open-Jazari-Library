/**
 * Birkaç Mesele-i Giring 1- web kamerasını kullanabilmeniz için projenizde
 * kullanacağınız jre x86 yani 32 bit olmalıdır. Çünkü kamera dll olan civil.dll
 * 32 bit de çalışabiliyor. Onun için eğer 64 bit sistemde çalışıyorsanız google
 * dan jdk 32 bit download ile oracle sitesinden x86 yazan java sürümünü indirip
 * kurduktan sonra project properties den x86 java jdk sını seçmek gerekiyor.
 * Bunu yapmak için project properties den manage platforms tıklanarak ilgili
 * jdk yı sisteme eklemelisiniz.
 */
package com.cezerilab.openjazarilibrary.device.webcam;

import com.cezerilab.openjazarilibrary.factory.FactoryImageProcess;
import com.cezerilab.openjazarilibrary.factory.FactoryUtils;
import com.lti.civil.CaptureDeviceInfo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author musa-atas
 */
public class WebCameraControlPanel extends javax.swing.JPanel {

    private WebCameraPanel panel_pict;
    private BufferedImage currBufferedImage;
    private BufferedImage originalBufferedImage;
    private WebCameraInterface camInt;
    List<WebCameraInterface> listeners = new ArrayList<WebCameraInterface>();

    public void addListener(WebCameraInterface frm) {
        listeners.add(frm);
    }

    public void sendWebCamStarted() {
        // Notify everybody that may be interested.
        for (WebCameraInterface hl : listeners) {
            hl.isWebCameraStarted();
        }
    }

    public void startCamera(int index) {
        btn_detectCD.doClick();
        combo_capture_device.setSelectedIndex(index);
        btnStartCamera();
    }

    public void startCamera(String id) {
        btn_detectCD.doClick();
        combo_capture_device.setSelectedItem(id);
        btnStartCamera();
    }

    /**
     * Creates new form CameraControlPanel
     * @param pp:int
     * @param calledFrame:int
     */
    public WebCameraControlPanel(WebCameraPanel pp, WebCameraInterface calledFrame) {
        this.camInt = calledFrame;
        addListener(camInt);
        panel_pict = pp;
        initComponents();
        this.setVisible(true);
        this.setSize(100, 200);
        repaint();
    }

    public WebCameraPanel getPicturePanel() {
        return panel_pict;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        btn_videoLive = new javax.swing.JButton();
        btn_snapShot = new javax.swing.JButton();
        btn_saveAsJPG = new javax.swing.JButton();
        btn_loadFromFile = new javax.swing.JButton();
        btn_startCam = new javax.swing.JButton();
        btn_edgeDetection = new javax.swing.JButton();
        btn_loadOriginal = new javax.swing.JButton();
        btn_stopCam = new javax.swing.JButton();
        combo_capture_device = new javax.swing.JComboBox();
        btn_detectCD = new javax.swing.JButton();
        btn_gray = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_videoLive.setText("Video Live");
        btn_videoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_videoLiveActionPerformed(evt);
            }
        });

        btn_snapShot.setText("Take SnapShot");
        btn_snapShot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_snapShotActionPerformed(evt);
            }
        });

        btn_saveAsJPG.setText("Save As (jpg)");
        btn_saveAsJPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveAsJPGActionPerformed(evt);
            }
        });

        btn_loadFromFile.setText("Load From File");
        btn_loadFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadFromFileActionPerformed(evt);
            }
        });

        btn_startCam.setText("Start CAMERA");
        btn_startCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startCamActionPerformed(evt);
            }
        });

        btn_edgeDetection.setText("Edge Detection");
        btn_edgeDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edgeDetectionActionPerformed(evt);
            }
        });

        btn_loadOriginal.setText("load original image");
        btn_loadOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadOriginalActionPerformed(evt);
            }
        });

        btn_stopCam.setText("Stop CAMERA");
        btn_stopCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopCamActionPerformed(evt);
            }
        });

        btn_detectCD.setText("Detect Capture Devices");
        btn_detectCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detectCDActionPerformed(evt);
            }
        });

        btn_gray.setText("To Gray Level");
        btn_gray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_loadFromFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_videoLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_snapShot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_saveAsJPG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_startCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_edgeDetection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_loadOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_stopCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_detectCD, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(combo_capture_device, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_gray, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_capture_device, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_detectCD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_startCam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_stopCam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btn_snapShot, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_videoLive, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_saveAsJPG, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_loadFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_loadOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edgeDetection, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        add(jPanel6);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_detectCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detectCDActionPerformed
        List<CaptureDeviceInfo> cd = panel_pict.getCaptureDevices();
        combo_capture_device.setModel(new DefaultComboBoxModel());
        addListener(camInt);
        for (CaptureDeviceInfo item : cd) {
            combo_capture_device.addItem(item.getDescription() + ":" + item.getDeviceID());
        }

    }//GEN-LAST:event_btn_detectCDActionPerformed

    private void btn_videoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_videoLiveActionPerformed
        panel_pict.isVideoLive = true;
    }//GEN-LAST:event_btn_videoLiveActionPerformed

    private void btn_snapShotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_snapShotActionPerformed
        currBufferedImage = panel_pict.takeSnapShot();
        // Notify everybody that may be interested.
        for (WebCameraInterface cam : listeners) {
            cam.sendSnapShot(currBufferedImage);
        }

    }//GEN-LAST:event_btn_snapShotActionPerformed

    private void btn_saveAsJPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveAsJPGActionPerformed
        saveSnapShot();
    }//GEN-LAST:event_btn_saveAsJPGActionPerformed

    private void btn_loadFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadFromFileActionPerformed
        panel_pict.isVideoLive = false;
        BufferedImage img = FactoryImageProcess.readImageFromFileWithDirectoryPath(".\\images\\snapshots");
        panel_pict.setImage(img);
        currBufferedImage = img;
    }//GEN-LAST:event_btn_loadFromFileActionPerformed

    private void btn_startCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startCamActionPerformed
        btnStartCamera();
    }//GEN-LAST:event_btn_startCamActionPerformed

    private void btnStartCamera() {
        panel_pict.stopCaptureStream();
        panel_pict.isVideoLive = true;
        panel_pict.startCaptureStream(combo_capture_device.getSelectedItem().toString());
        sendWebCamStarted();
    }

    private void btn_edgeDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edgeDetectionActionPerformed
        currBufferedImage = FactoryImageProcess.edgeDetectionCanny(currBufferedImage, 0.5f, 1.0f, 3.0f, 3, false);
        panel_pict.setImage(currBufferedImage);
    }//GEN-LAST:event_btn_edgeDetectionActionPerformed

    private void btn_loadOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadOriginalActionPerformed
        currBufferedImage = originalBufferedImage;
        panel_pict.setImage(currBufferedImage);
    }//GEN-LAST:event_btn_loadOriginalActionPerformed

    private void btn_stopCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopCamActionPerformed
        panel_pict.stopCaptureStream();
    }//GEN-LAST:event_btn_stopCamActionPerformed

    private void btn_grayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grayActionPerformed
        long startTime = System.nanoTime();
        long t1 = System.currentTimeMillis();
        currBufferedImage = FactoryImageProcess.pixelsToImageGray(FactoryImageProcess.imageToPixelsInt(currBufferedImage));
        long stopTime = System.nanoTime();
        System.out.println("gray elapsed time in nano:" + (stopTime - startTime) / 1000000.0);
        System.out.println("gray elapsed time in mili:" + (System.currentTimeMillis() - t1));
        panel_pict.setImage(currBufferedImage);
    }//GEN-LAST:event_btn_grayActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_detectCD;
    public javax.swing.JButton btn_edgeDetection;
    public javax.swing.JButton btn_gray;
    public javax.swing.JButton btn_loadFromFile;
    public javax.swing.JButton btn_loadOriginal;
    public javax.swing.JButton btn_saveAsJPG;
    public javax.swing.JButton btn_snapShot;
    public javax.swing.JButton btn_startCam;
    public javax.swing.JButton btn_stopCam;
    public javax.swing.JButton btn_videoLive;
    public javax.swing.JComboBox combo_capture_device;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables

    public void saveSnapShot() {
        panel_pict.isVideoLive = true;
        panel_pict.saveSnapShot();
    }

    public void saveSnapShot(String filePathAndName, String imgType) {
        panel_pict.isVideoLive = true;
        panel_pict.saveSnapShot(filePathAndName, imgType);
        panel_pict.isVideoLive = true;
    }
}
