/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author vantuyen361
 */
public class Directory {

    /**
     * Convert relative path start from project folder to absolute path.
     *
     * @param rel relative path start from project folder. Example: "guide\\readme.txt"
     * @return absolute path.
     */
    public String convertPathFromProjectFolder(String rel) {
        File file = new File(rel);
        return file.getAbsolutePath();
    }

    /**
     * Creates the directory named by this abstract pathname.
     *
     * @param path
     * @return true if all directory created, false if otherwise
     */
    public boolean mkdirs(String path) {
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
    public File fileFromClassPath(String rel) throws URISyntaxException {
        URL url = this.getClass().getResource(rel);
        File file;
        file = Paths.get(url.toURI()).toFile();
        return file;
    }
    
    public String pathOfCurrentProject(){
        return System.getProperty("user.dir");
    }
    
    public void showAllSystemProperties(){
        Properties properties = System.getProperties();
        Enumeration<Object> enumeration = properties.keys();
        while(enumeration.hasMoreElements()){
            Object obj = enumeration.nextElement();
            StringBuffer str;
            str = new StringBuffer("Key: ");
            str.append(obj.toString());
            str.append("\t\tValue: ");
            str.append(properties.getProperty(obj.toString()));
            System.out.println(str.toString());
        }
    }
}
