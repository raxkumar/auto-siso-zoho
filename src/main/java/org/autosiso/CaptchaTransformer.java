//package org.autosiso;
//
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class CaptchaTransformer   {
//  public static void main(String[] args) throws  IOException, TesseractException {
//
//    CaptchaCleaner cc = new CaptchaCleaner();
//    CaptchaCleanResult ccr = new CaptchaCleanResult();
//
//    BufferedImage image = ImageIO.read(new File("/home/rakesh/Desktop/orc/sample/captcha-sample-1.png"));
//    BufferedImage clean = cc.cleanImage(image);
//    ImageIO.write(clean, "png", new File("/home/rakesh/Desktop/clean.png"));
//    Tesseract tesseract = new Tesseract();
//    tesseract.setDatapath("/home/rakesh/Desktop/orc/tessdata");
//    String result = tesseract.doOCR(clean);
//    String finalResult = ccr.cleanResult(result);
//    System.out.println(finalResult);
//
//  }
//}
