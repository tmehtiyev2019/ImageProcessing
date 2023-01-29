import java.awt.image.BufferedImage;
import javax.swing.*;


public class Coordinates extends Thread{

    JFrame frm;
    BufferedImage input_image;
    int square_size;
    int widthStart, widthEnd, heightStart, heightEnd;

    public Coordinates(JFrame frm, BufferedImage input_image, int square_size) {
        this.frm = frm;
        this.input_image = input_image;
        this.square_size = square_size;
    }
    public void run() {
        Processing processing = new Processing(frm, input_image, square_size);
        processing.convert(widthStart, widthEnd, heightStart, heightEnd);
    }

    public void setHeightStart(int heightStart) {
        this.heightStart = heightStart;
    }

    public void setHeightEnd(int heightEnd) {
        this.heightEnd = heightEnd;
    }

    public void setWidthStart(int widthStart) {
        this.widthStart = widthStart;
    }

    public void setWidthEnd(int widthEnd) {
        this.widthEnd = widthEnd;
    }




}
