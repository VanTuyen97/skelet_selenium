/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 *
 * @author vantuyen361
 */
public class Directory {

    /**
     * Convert relative path start from project folder to absolute path.
     *
     * @param rel relative path start from project folder. Example:
     * "guide\\readme.txt"
     * @return absolute path.
     */
    public static String convertPathFromProjectFolder(String rel) {
        File file = new File(rel);
        return file.getAbsolutePath();
    }

    /**
     * Creates the directory named by this abstract pathname.
     *
     * @param path
     * @return true if all directory created, false if otherwise
     */
    public static boolean mkdirs(String path) {
        File dir = new File(path);
        return dir.mkdirs();
    }

    /**
     * Get file from class path.
     *
     * @param rel name file. Example: "/application.properties".
     * @return
     * @throws URISyntaxException
     */
    public static File fileFromClassPath(String rel) throws URISyntaxException {
        URL url = Directory.class.getResource(rel);
        File file;
        file = Paths.get(url.toURI()).toFile();
        return file;
    }

    public static String pathOfCurrentProject() {
        return System.getProperty("user.dir");
    }

    public static void showAllSystemProperties() {
        Properties properties = System.getProperties();
        Enumeration<Object> enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            StringBuffer str;
            str = new StringBuffer("Key: ");
            str.append(obj.toString());
            str.append("\t\tValue: ");
            str.append(properties.getProperty(obj.toString()));
            System.out.println(str.toString());
        }
    }

    /**
     * Capture screen and save in directory specific.
     * @param directory the path of directory to save image. Example: "D:\\project github\\MIB3_CB\\Screen".
     * @return path of image
     * @throws IOException
     * @throws AWTException 
     */
    public static String captureScreen(String directory) throws IOException, AWTException {
        StringBuilder path = new StringBuilder();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        path.append(directory);
        path.append("\\Capture Screen ");
        path.append(format.format(time));
        path.append(".jpg");

        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        ImageIO.write(screenFullImage, "jpg", new File(path.toString()));
        return path.toString();
    }
}
