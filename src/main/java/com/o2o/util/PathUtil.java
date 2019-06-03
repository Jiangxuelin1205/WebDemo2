package com.o2o.util;

public class PathUtil {
    /**
     * 返回项目图片的根路径
     * 和返回项目的子路径
     */
    private static String seperator = System.getProperty("file.separator");

    //处理缩略图

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "G:/IntelleJIdeaWorkingPlace/WebDemo/src/main/java/com/o2o/images/";
        } else {
            basePath = "/home/image";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        String imagePath = "upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
