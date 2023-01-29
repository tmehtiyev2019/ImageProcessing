import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;


public class Main {
    static JFrame frm;
    static BufferedImage input_image;
    static int square_size, image_width, image_height;
    static String file_adress, processing_mode;

    public static void main(String[] args) {
        file_adress = args[0];
        square_size = Integer.parseInt(args[1]);
        processing_mode = args[2];
        convert_image();
    }

    private static void singleMode() throws IOException {
        Processing processing = new Processing(frm, input_image, square_size);
        processing.convert(0, image_width, 0, image_height);
    }

    private static void multiMode() throws IOException {
        int thread_number = Runtime.getRuntime().availableProcessors();
        int portion = image_height / thread_number; // dividing the image horizontally into equal parts by considering available cores.     
        Coordinates[] cores = new Coordinates[thread_number];

        for (int i = 1; i <= thread_number; i++) {
            // equally divided parts of the picture are assigned to
            int core_start = (i-1) * portion; //defining the starting height point for each core
            int core_end = i * portion; //defining the ending height point for each core

            Coordinates coordinates = new Coordinates(frm, input_image, square_size); // create thread for each core
         
            coordinates.setWidthStart(0);
            coordinates.setWidthEnd(image_width);
            coordinates.setHeightStart(core_start);
            coordinates.setHeightEnd(core_end);

            coordinates.start();
            cores[i-1] = coordinates;
        }

        try {
            for (int i = 0; i < thread_number; i++) {
                cores[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void convert_image() {
        try {
            input_image = ImageIO.read(new File(file_adress));
            JLabel label = new JLabel(new ImageIcon(input_image));

            frm = new JFrame();
            frm.add(label);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.pack();
            frm.setVisible(true);

            image_width = input_image.getWidth();
            image_height = input_image.getHeight();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            if (image_width > screenWidth || image_height > screenHeight) {
                frm.setSize(screenSize.width, screenSize.height);
            }

            if (processing_mode.equals("S")) { 
                frm.setTitle("Single Threaded Processing Mode");
                singleMode();
            }
            else if (processing_mode.equals("M")) { 
                frm.setTitle("Multi Threaded Processing Mode");
                multiMode();
            }
            else {
                System.out.println("You have entered incorrect thread processing mode!");
            }

            ImageIO.write(input_image, "jpg", new File("result.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}