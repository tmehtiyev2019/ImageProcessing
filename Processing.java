
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;



public class Processing {
    JFrame frm;
    BufferedImage input_image;
    int square_size;

    public Processing(JFrame frm, BufferedImage input_image, int square_size) {
        this.frm = frm;
        this.input_image = input_image;
        this.square_size = square_size;
    }

    public void convert(int widthStart, int widthEnd, int heightStart, int heightEnd) {
        for (int i = heightStart; i < heightEnd; i += square_size) { 
            for (int j = widthStart; j < widthEnd; j += square_size) { 

                for (int k = 0; k < square_size; k++) {
                    for (int l = 0; l < square_size; l++) {
                        
                        if (i + k < heightEnd && j + l < widthEnd) { 

                            int pixel = input_image.getRGB(j + l, i + k);
                            Color color = new Color(pixel);

                            // Customized conditional adjustment to the gray color based on the RGB codes

                            if (color.getRed()<130 && color.getGreen()<130 && color.getBlue()<130 )
                            {Color col = new Color(20, color.getGreen(), 20);
                            input_image.setRGB(j + l, i + k, col.getRGB());}


                            else if (color.getRed()<150 && color.getGreen()<150 && color.getBlue()<150 )
                            {Color col = new Color(color.getRed(), color.getGreen(), 20);
                            input_image.setRGB(j + l, i + k, col.getRGB());}


                            else if (color.getRed()<170 && color.getGreen()<170 && color.getBlue()<170 )
                            {Color col = new Color(color.getRed(), color.getGreen(), 5);
                            input_image.setRGB(j + l, i + k, col.getRGB());}

                            else if (color.getRed()<200 && color.getGreen()<200 && color.getBlue()<200 )
                            {Color col = new Color(150, 150, color.getBlue());
                            input_image.setRGB(j + l, i + k, col.getRGB());}
                            
                            else 
                            {Color col = new Color(color.getRed(), color.getGreen(),150);
                            input_image.setRGB(j + l, i + k, col.getRGB());}

                        }
                    }
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frm.validate();
                frm.repaint();
            }
        }
    }
}