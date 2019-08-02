package hk.reco.music.iptv.common.utils.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author wangpq
 * @Date 2019/2/27 16:00
 * @Version 1.0
 */
public class ExcelUtil {

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param path     文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(Path path, BaseRowModel rowModel) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(path, excelListener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            if (rowModel != null) {
                sheet.setClazz(rowModel.getClass());
            }
            reader.read(sheet);
        }
        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param path     文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(Path path, BaseRowModel rowModel, int sheetNo) {
        return readExcel(path, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param path        文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(Path path, BaseRowModel rowModel, int sheetNo,
                                         int headLineNum) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(path, excelListener);
        if (reader == null) {
            return null;
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));
        return excelListener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object    映射实体类，Excel 模型
     */
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> list,
                                  String fileName, String sheetName, BaseRowModel object) {
        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 功能描述:
     * 〈数据写入excel〉
     *
     * @param list      待写入数据
     * @param fileName  写入文件名称
     * @param typeEnum  文件格式
     * @param sheetName 工作表
     * @param cla       对应Class
     * @return : void
     * @author : wangpq
     * @date : 2019/8/1 13:16
     */
    public static void writeExcel(List<? extends BaseRowModel> list, String fileName, ExcelTypeEnum typeEnum,
                                  String sheetName, Class<? extends BaseRowModel> cla) throws Exception {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        try (OutputStream out = new FileOutputStream(path.toFile())) {
            ExcelWriter writer = new ExcelWriter(out, typeEnum);
            Sheet sheet1 = new Sheet(1, 0, cla);
            sheet1.setSheetName(sheetName);
            writer.write(list, sheet1);
            writer.finish();
        }
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object    映射实体类，Excel 模型
     */
    public static ExcelWriterFactroy writeExcelWithSheets(HttpServletResponse response, List<? extends BaseRowModel> list,
                                                          String fileName, String sheetName, BaseRowModel object) {
        ExcelWriterFactroy writer = new ExcelWriterFactroy(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        return writer;
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        //创建本地文件
        String filePath = fileName + ".xlsx";
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            throw new ExcelException("创建文件失败！");
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param path          需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(Path path,
                                         ExcelListener excelListener) {
        String filename = path.toFile().getName();
        if (!filename.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue())
                && !filename.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(Files.newInputStream(path));
            return new ExcelReader(inputStream, null, excelListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
