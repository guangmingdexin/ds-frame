package com.guang.provider.common.util;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
public class CommonUtil {


    public static String getFileExtension(String fileName) {

        if (fileName == null) {
            return null;
        }

        int i = fileName.lastIndexOf('.');
        if(i == -1) {
            // 该文件没有扩展名
            System.out.println(fileName + " 没有扩展名！");
            return null;
        }
        return fileName.substring(i + 1);
    }

}
