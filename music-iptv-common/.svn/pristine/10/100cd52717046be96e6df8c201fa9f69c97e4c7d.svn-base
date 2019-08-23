package hk.reco.music.iptv.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.*;
import java.util.List;

/**
 * @author wangpq
 * @version 1.0
 * @className FileUtils
 * @description TODO
 * @date 2019/8/2 20:23
 */
public class FileUtils {

    /**
     * 功能描述:
     * 〈获取指定路径下的文件〉
     *
     * @param path 文件路径 D:\test
     * @return : java.util.List<java.nio.file.Path>
     * @author : wangpq
     * @date : 2019/8/2 20:23
     */
    public static List<Path> getPaths(String path) {
        return getPaths(path, null);
    }

    /**
     * 功能描述:
     * 〈获取指定路径下文件 并过滤指定格式〉
     *
     * @param path 文件路径 D:\test
     * @param format *.xls
     * @return : java.util.List<java.nio.file.Path>
     * @author : wangpq
     * @date : 2019/8/2 20:24
     */
    public static List<Path> getPaths(String path, String format) {
        List<Path> list = Lists.newArrayList();
        if (org.apache.commons.lang3.StringUtils.isBlank(path)) {
            return list;
        }
        Path p = Paths.get(path);
        if (Files.exists(p)) {
            try {
                DirectoryStream<Path> paths = StringUtils.isBlank(format) ? Files.newDirectoryStream(p) : Files.newDirectoryStream(p, format);
                paths.forEach(list::add);
                paths.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    /**
     * 功能描述:
     * 〈文件重命名〉
     *
     * @param fileName 文件名 D:\test\123.txt
     * @param newFileName 新文件名 D:\test\456.txt
     * @return : void
     * @author : wangpq
     * @date : 2019/8/2 20:25
     */
    public static void rename(String fileName, String newFileName) {
        rename(fileName, newFileName, false);
    }

    /**
     * 功能描述:
     * 〈文件重命名〉
     *
     * @param fileName 文件名 D:\test\123.txt
     * @param newFileName 新文件名 D:\test\456.txt
     * @param deleteOldFile 是否删除
     * @return : void
     * @author : wangpq
     * @date : 2019/8/2 20:25
     */
    public static void rename(String fileName, String newFileName, boolean deleteOldFile) {
        File file = Paths.get(fileName).toFile();
        File oldFile = Paths.get(newFileName).toFile();
        file.renameTo(oldFile);
        if (deleteOldFile) {
            delete(fileName);
        }
    }

    /**
     * 功能描述:
     * 〈删除文件〉
     *
     * @param fileName 文件名 D:\test\123.txt
     * @return : boolean
     * @author : wangpq
     * @date : 2019/8/2 20:27
     */
    public static boolean delete(String fileName) {
        try {
            return Files.deleteIfExists(Paths.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 功能描述:
     * 〈拷贝文件〉
     *
     * @param sharePath 文件地址 D:\test\123.txt
     * @param localPath 存储地址 E:\test\
     * @param fileName  文件名称 456.txt
     * @return : void
     * @author : wangpq
     * @date : 2019/7/31 19:15
     */
    public static void copyFile(String sharePath, String localPath, String fileName) throws Exception {
        Path path = Paths.get(localPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        path = Paths.get(localPath + fileName);
        Path share = Paths.get(sharePath);
        long size = Files.size(share);
        if (size <= 0) {
            throw new Exception("文件大小0KB");
        }
        Path copy = Files.copy(Paths.get(sharePath), path, StandardCopyOption.REPLACE_EXISTING);
        if (size != Files.size(copy)) {
            throw new Exception("源文件与拷贝后的文件大小不一致");
        }
    }

}
