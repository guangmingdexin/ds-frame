package com.common.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guangmingdexin
 * @date 2022/4/17
 */
public class FileTools {

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    /** -----------------------------目前可以识别的类型----------------------------*/
    private static void getAllFileType()
    {
        //JPEG
        FILE_TYPE_MAP.put("jpg", "FFD8FF");
        //PNG
        FILE_TYPE_MAP.put("png", "89504E47");
        //GIF
        FILE_TYPE_MAP.put("gif", "47494638");
        //TIFF
        FILE_TYPE_MAP.put("tif", "49492A00");
        //Windows Bitmap
        FILE_TYPE_MAP.put("bmp", "424D");
        //CAD
        FILE_TYPE_MAP.put("dwg", "41433130");
        //HTML
        FILE_TYPE_MAP.put("html", "68746D6C3E");
        //Rich Text Format
        FILE_TYPE_MAP.put("rtf", "7B5C727466");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        //PhotoShop
        FILE_TYPE_MAP.put("psd", "38425053");
        //Email [thorough only]
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
        //Outlook Express
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
        //Outlook
        FILE_TYPE_MAP.put("pst", "2142444E");
        //office类型，包括doc、xls和ppt
        FILE_TYPE_MAP.put("office", "D0CF11E0");

        // office-x 类型 （包括 docx, xlsx）
        FILE_TYPE_MAP.put("office-x", "504B03040A00000000");



        //MS Access
        FILE_TYPE_MAP.put("mdb", "000100005374616E64617264204A");
        //WordPerfect
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        //Adobe Acrobat
        FILE_TYPE_MAP.put("pdf", "255044462D312E");
        //Quicken
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
        //Windows Password
        FILE_TYPE_MAP.put("pwl", "E3828596");
        //Wave
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");
        //Real Audio
        FILE_TYPE_MAP.put("ram", "2E7261FD");
        //Real Media
        FILE_TYPE_MAP.put("rm", "2E524D46");
        FILE_TYPE_MAP.put("mpg", "000001BA");
        //Quicktime
        FILE_TYPE_MAP.put("mov", "6D6F6F76");
        //Windows Media
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
        //MIDI (mid)
        FILE_TYPE_MAP.put("mid", "4D546864");
    }


    /**
     *
     * 判断文件类型是否正确
     *
     * @param file 文件
     * @param isTemp 是否为临时创建的文件（或者是否需要删除）
     * @param type 文件类型
     * @return boolean
     * @throws Exception
     */
    public static boolean check(File file, boolean isTemp, String ... type) throws Exception {

        String fileType = getFileType(new FileInputStream(file), file.getName());

        if(isTemp) {

            file.deleteOnExit();
//            if(!file.delete()) {
//                System.out.println("临时文件删除失败: " + file.getAbsolutePath());
//            }
        }

        return Arrays.asList(type).contains(fileType);

    }


    /**
     * 通过读取文件头部获得文件类型
     * @param is 源文件流
     * @param fileName 文件名称
     * @return 文件类型
     * @throws Exception
     */
    public static String getFileType(InputStream is, String fileName) throws Exception {
        getAllFileType();
        String fileExtendName = null;

        try {

            byte[] b = new byte[16];
            int r = is.read(b,0, b.length);

            String filetypeHex = String.valueOf(bytesToHexString(b));

            for (Map.Entry<String, String> entry : FILE_TYPE_MAP.entrySet()) {
                String fileTypeHexValue = entry.getValue();
                if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                    fileExtendName = entry.getKey();
                    if ("office".equals(fileExtendName)) {
                        fileExtendName = getOfficeFileType((FileInputStream) is);
                    }else if("office-x".equals(fileExtendName)) {
                        fileExtendName = getFileExtension(fileName);
                    }
                    is.close();
                    break;
                }
            }

            if(fileExtendName == null) {
                fileExtendName = getFileExtension(fileName);
            }
            is.close();
            return fileExtendName;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    public static String getFileExtension(String fileName) {
        // 如果不是上述类型，则判断扩展名
        // 如果无扩展名，则直接返回空串
        if(!fileName.contains(".")) {
            return "";
        }
        // 如果有扩展名，则返回扩展名
        return fileName.substring(fileName.indexOf(".") + 1);
    }


    /**
     * 判断office文件的具体类型
     * @param fileInputStream
     * @return office文件具体类型
     * @throws Exception
     */
    private static String getOfficeFileType(FileInputStream fileInputStream) throws Exception {
        String officeFileType;
        byte[] b = new byte[512];
        try {
            int r = fileInputStream.read(b, 0, b.length);
            String filetypeHex = String.valueOf(bytesToHexString(b));
            String flagString = filetypeHex.substring(992);
            if(flagString.toLowerCase().startsWith("eca5c")){
                officeFileType = "doc";
            } else if(flagString.toLowerCase().startsWith("fdffffff09")
            || flagString.toLowerCase().startsWith("09081000000")){
                officeFileType = "xls";
            }  else {
                officeFileType = "ppt";
            }
            return officeFileType;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }


    /**
     * 获得文件头部字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){

        if (src == null || src.length <= 0) {
            return null;
        }

        System.out.println("data: " + Arrays.toString(src));

        return new BigInteger(1, src).toString(16);

    }

}
