package com.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class ImageUtil {

    private static String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将commonsMultipartFile转换成file
     */
    public static File transferMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    public static String generateThumbnails(InputStream fileInputStream, String fileName, String targetAddress) {
        String realFileName = getRandomFileName();//由于用户传入的图片可能重名，因此生成一个新的名称
        String extension = getFileExtension(fileName);//获取图片的扩展名
        makeDirPath(targetAddress);//生成图片存储的绝对路径
        String relativeAddress = targetAddress + realFileName + extension;//生成图片的相对地址
        logger.debug("current relative address is " + relativeAddress);
        File destination = new File(PathUtil.getImgBasePath() + relativeAddress);//生成图片的绝对地址
        logger.debug("current complete address is " + PathUtil.getImgBasePath() + relativeAddress);
        try {
            Thumbnails.of(fileInputStream).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(destination);//将旧的图片重命名并且加工之后生成新的图片
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddress;
    }

    /**
     * 创建目标路径涉及的目录
     * 如果目标文件的目录不存在，该目录需要被创建
     **/
    private static void makeDirPath(String targetAddress) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddress;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dirPath.mkdirs();
        }
    }

    /**
     * 获取文件扩展名
     * 用于文件的格式为xx.jpg或者xxx.png，因此获取到最后一个.的位置就可以了
     **/
    private static String getFileExtension(String fileName) {
        //String originFileName = file
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成指定格式的随机文件名，生成方法为 当前时间+一个随机的五位数
     */
    private static String getRandomFileName() {
        //获取随机的五位数
        int randomNumber = r.nextInt(89999) + 10000;
        String now = sDateFormat.format(new Date());
        return now + randomNumber;
    }

    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("./src/main/resources/blackandwhite.jpg")).size(500, 500)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("./src/main/resources/newblackandwhite.jpg");
    }

    /**
     * 判断storePath是文件还是路径。如果是路径，则删除改路径下所有的文件，如果是文件，则删除该文件。
     **/
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                assert files != null;
                for (File file : files) {
                    //noinspection ResultOfMethodCallIgnored
                    file.delete();
                }
            }
            //noinspection ResultOfMethodCallIgnored
            fileOrPath.delete();
        }
    }
}
