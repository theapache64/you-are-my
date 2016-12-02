import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.javafx.font.FontFactory;
import database.tables.BaseTable;
import database.tables.Requests;
import database.tables.Words;
import models.Request;
import models.Word;
import utils.FontUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static java.awt.SystemColor.text;

/**
 * Created by theapache64 on 2/12/16.
 */
@WebServlet(urlPatterns = {"/you-are-my.png"})
public class YouAreMyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {


        //Getting word
        final Word randomWord = Words.getInstance().getRandomWord();

        //Adding request
        try {
            Requests.getInstance().add(new Request(randomWord.getId(), req.getRemoteAddr()));

            final String text = "You're my " + randomWord.getWord();
            final Font font = FontUtils.getRobotoRegular(getServletContext());

            // create temporary 1x1 image to get FontRenderingContext needed to calculate image size
            BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            FontRenderContext fc = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(text, fc);

// calculate the size of the text
            int width = (int) bounds.getWidth();
            int height = (int) bounds.getHeight();

// prepare final image with proper dimensions
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g2 = buffer.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(font);

// actually do the drawing
            g2.setColor(Color.white);
            g2.fillRect(0, 0, width, height);
            g2.setColor(Color.black);
            g2.drawString(text, 0, (int) -bounds.getY());

// set the content type, get the output stream and print image as PNG
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(buffer, "png", os);
            os.close();


        } catch (BaseTable.InsertFailedException | FontFormatException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }

    }
}
