/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public abstract class ImageExporter {
    public static void saveImage(Component requester, JPanel toExport, String imageType) {
        String upperImageType = imageType.toUpperCase();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(upperImageType+" images",imageType));
        if (chooser.showSaveDialog(requester) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file.exists()) {
                if (file.getName().endsWith("."+imageType)) {
                    int confirm = JOptionPane.showConfirmDialog(requester,
                        "This file already exists.\n Override ?",
                        "Override confirmation",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.NO_OPTION) {
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(requester, 
                            "The selected file is not a "+upperImageType+" file.",
                            "Saving error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (!file.getName().endsWith("."+imageType)) {
                file = new File(file.getAbsolutePath()+"."+imageType);
            }
            
            // Capture the panel as image
            Dimension d = toExport.getPreferredSize();
            BufferedImage image = new BufferedImage(
                    (int)d.getWidth(), 
                    (int)d.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);
            
            toExport.paint(image.getGraphics());
            try {
                ImageIO.write(image, imageType, file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(requester, 
                        "An error occured while saving the image file.",
                        "Saving error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
