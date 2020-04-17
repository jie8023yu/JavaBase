package com.com.wj.designpattern.project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

public class Test {

    public static void main(String[] args) throws IOException {
        /*BufferedImage image = ImageIO.read(new File("I:\\ws\\JavaBase\\JavaBase\\java\\src\\com\\com\\wj\\designpattern\\project\\images\\0.gif"));
        assertNotNull(image);

        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("com/com/wj/designpattern/project/images/0.gif");
        assertNotNull(resourceAsStream);
        URL resource = Test.class.getClassLoader().getResource("com/com/wj/designpattern/project/images/0.gif");
        assertNotNull(resource);
        System.out.println(1);*/

        BufferedImage image = ResourceMgr.tankL;
    }
}
