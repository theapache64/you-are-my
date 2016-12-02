package utils;

import javax.servlet.ServletContext;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by theapache64 on 2/12/16.
 */
public class FontUtils {
    private static Font robotoRegular;

    public static Font getRobotoRegular(ServletContext servletContext) throws IOException, FontFormatException {
        if (robotoRegular == null) {
            final String fontPath = servletContext.getRealPath("WEB-INF/fonts/roboto.regular.ttf");
            robotoRegular = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(fontPath));
            robotoRegular = robotoRegular.deriveFont(65f);
        }
        return robotoRegular;
    }
}
