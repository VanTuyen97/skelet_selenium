/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.util;

import java.io.File;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vantuyen361
 */
@RunWith(SpringRunner.class)
public class DirectoryTest {

    @Test
    public void testSomeMethod() {
        String path = Directory.convertPathFromProjectFolder("guide\\readme.txt");
        Assert.assertTrue("convert path failure, path:" + path, path.contains("skelet_selenium\\guide\\readme.txt"));
    }

    @Test
    public void testFileFromClassPath() throws URISyntaxException {
        try {
            File file = Directory.fileFromClassPath("/application.properties");
        } catch (NullPointerException e) {
            Assert.assertTrue("not found file.", false);
        }
    }

    @Test
    public void testPathOfCurrentProject() throws URISyntaxException {
        String path = Directory.pathOfCurrentProject();
        Assert.assertTrue("don't get path of current project.", path.contains("skelet_selenium"));
    }

    @Test
    public void testShowAllSystemProperties(){
        Directory.showAllSystemProperties();
    }
}
