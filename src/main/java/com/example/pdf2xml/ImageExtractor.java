package com.example.pdf2xml;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 *  This class is used to extract images from pdf
 * Author: Aditya
 **/
public class ImageExtractor {

    /**
     * Extracts images from pdf
     * @param document
     * @param path
     * @throws IOException
     */
    public static void extractImages(PDDocument document,String path) throws IOException {

        PDPageTree list = document.getPages();
        int imageID = 1;
        for (PDPage page : list) {
            PDResources pdResources = page.getResources();
            for (COSName name : pdResources.getXObjectNames()) {
                PDXObject o = pdResources.getXObject(name);
                if (o instanceof PDImageXObject) {
                    PDImageXObject image = (PDImageXObject) o;
                    String filename =path+ "/extracted-image-" + imageID + ".png";
                    System.out.println(filename);
                    ImageIO.write(image.getImage(), "png", new File(filename));
                    imageID++;
                }
            }
        }
    }
}
