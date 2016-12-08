package com.iermu.myapplication;

import android.provider.Settings;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.Scalar;
/**
 * Created by 建敏 on 2016/12/8.
 */

public class UiTest extends UiAutomatorTestCase {

    public void testDemo() throws UiObjectNotFoundException {

        System.out.println("++++" + "start ui testing");
        OpenCVLoader.initDebug();
        areaDetect("/mnt/sdcard/Screenshot.png","mnt/sdcard/s1.png");

    }

    private static Mat scaleImg(String sourceImg)
    {
        double resizeRate = 0.67;
        Mat img = Imgcodecs.imread(sourceImg);
        int width = img.cols();
        int height = img.rows();

        System.out.println("+++"+ width + ":" + height);

        Mat resizeImage = new Mat();

        Size sz = new Size(resizeRate * width, resizeRate* height);

        Imgproc.resize(img, resizeImage, sz);
        return resizeImage;


    }

    private static void areaDetect(String sourceImg, String templateImg)
    {
        Mat img = Imgcodecs.imread(sourceImg);
        Mat templ = Imgcodecs.imread(templateImg);


        int result_cols = img.cols() - templ.cols() +1;
        int result_rows = img.rows() - templ.rows() +1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_SQDIFF);

        Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

        MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;

        matchLoc = mmr.minLoc;


        double threashhold = 0.40;
        if (mmr.maxVal > threashhold) {
            Imgproc.rectangle (img, matchLoc, new Point(matchLoc.x + templ.cols(),
                    matchLoc.y + templ.rows()), new Scalar(0, 255, 0));
        }
        // Save the visualized detection.
    //    Highgui.imwrite(destinatioImg, img);

        System.out.println("+++"+ matchLoc.x + ":" + matchLoc.y );

        //模板图片在原图中的中心点
        System.out.println("+++"+ (matchLoc.x + templ.cols()/2) + ":" + (matchLoc.y + templ.rows()/2) );

    }
}
