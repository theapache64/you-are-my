import com.sun.image.codec.jpeg.JPEGCodec;
import database.tables.Words;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by theapache64 on 2/12/16.
 */
@WebServlet(urlPatterns = {"/you-are-my.png"})
public class YouAreMyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // set mime type as jpg image
        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();

        BufferedImage image = new BufferedImage(200, 40, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();


        // Set back ground of the generated image to white
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 200, 40);

        // set gradient font of text to be converted to image
        graphics.setColor(Color.BLACK);
        Font font = new Font("Arial Black", Font.BOLD, 30);
        graphics.setFont(font);

        // write 'Hello World!' string in the image
        graphics.drawString(Words.getInstance().getRandomWord().getWord(), 5, 30);

        // release resources used by graphics context
        graphics.dispose();

        // encode the image as a JPEG data stream and write the same to servlet output stream
        //JPEGCodec.createJPEGEncoder(out).encode(image);
        ImageIO.write(image, "png", out);

        // close the stream
        out.close();

    }
}
