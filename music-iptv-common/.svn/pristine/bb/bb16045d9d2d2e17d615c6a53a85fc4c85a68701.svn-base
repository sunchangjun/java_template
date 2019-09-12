package hk.reco.music.iptv.common.utils.excel;


import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ：suncj
 * @date ：2019/8/21 14:25
 */
public class CsvUtils {

    /**
     * 读取CSV文件
     *
     * @param csvFilePath 文件路径
     */
    public static List<String[]> readeCsv(String csvFilePath) {
        ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
        try {
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK")); // 一般用这编码读就可以了
            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(reader.getValues());
                System.out.println(JSONObject.toJSONString(reader.getValues()));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return csvList;
    }

    /**
     * 写入csv结束，写出流
     */
    public void outCsvStream(HttpServletResponse response, File tempFile) throws IOException {
        java.io.OutputStream out = response.getOutputStream();
        byte[] b = new byte[10240];
        java.io.File fileLoad = new java.io.File(tempFile.getCanonicalPath());
        response.reset();
        response.setContentType("application/csv");
        response.setHeader("content-disposition", "attachment; filename=export.csv");
        java.io.FileInputStream in = new java.io.FileInputStream(fileLoad);
        int n;
        //为了保证excel打开csv不出现中文乱码
        out.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
        while ((n = in.read(b)) != -1) {
            //每次写入out1024字节
            out.write(b, 0, n);
        }
        in.close();
        out.close();
    }

    /**
     * 读取CSV文件
     *
     * @param csvFilePath 文件路径
     */
    public static List<String> readeCsvRow(String csvFilePath, int row) {
        ArrayList<String> csvList = new ArrayList<String>(); // 用来保存数据
        try {
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK")); // 一般用这编码读就可以了
            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(reader.getValues()[row]);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return csvList;
    }
    /**
     * 创建临时的csv文件
     * @return
     * @throws IOException
     */
    public File createTempFile(List datas) throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"行为明细", "执行对象","触发时间","状态"};
        csvWriter.writeRecord(headers);
//        for (ActionDetails data : datas) {
//            //这里如果数据不是String类型，请进行转换
//            csvWriter.write(data.getContent());
//            csvWriter.write(data.getObjectName());
//            csvWriter.write(data.getCreateTime());
//            csvWriter.write(data.getStatus());
//            csvWriter.endRecord();
//        }
        csvWriter.close();
        return tempFile;
    }
    private static List<List<Object>> getNovel() {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < 165; i++) {
            rowList = new ArrayList<Object>();
            Object[] row = new Object[4];
            row[0] = i;
            row[1] = "风云第一刀"+i+"";
            row[2] = "古龙"+i+"";
            row[3] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            for(int j=0;j<row.length;j++){
                rowList.add(row[j]);
            }
            dataList.add(rowList);
        }
        return dataList;
    }

    /**
     * CSV文件生成方法
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile1(List<Object> head, List<List<Object>> dataList,String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }


    /**
     * CSV文件生成方法
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile11(List<Object> head, List<List<Object>> dataList,String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }




    public static void exportCsv(HttpServletResponse response , HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        // 设置表格头
        Object[] head = {"序号","小说名称","作者","出版日期"};
        List<Object> headList = Arrays.asList(head);
        List<List<Object>> dataList = getNovel();
        // 导出文件路径
        String downloadFilePath = request.getSession().getServletContext().getRealPath("");
        // 导出文件名称
        String  fileName = "download";
        // 导出CSV文件
        File csvFile = createCSVFile(headList, dataList, downloadFilePath, fileName);

//########################################################################
        try {



            // 取得文件名。
            String filename = csvFile.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
//            InputStream fis = new FileInputStream(csvFile);
            FileInputStream fis = new FileInputStream(csvFile);
            // 设置response的Header

            String userAgent = request.getHeader("User-Agent");
//            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", filename));
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");


            int content = 0;
            while ((content = fis.read()) != -1) {
                toClient.write(content);
            }
            fis.close();
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

//#############################################################################

        long endTime = System.currentTimeMillis();
        System.out.println("整个CSV导出"+(endTime-startTime));
    }

    /**
     * CSV文件生成方法
     *
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);
            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    public static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    /**
     * 追加数据:不建议使用
     * @param row
     * @param csvPath
     * @throws IOException
     */
    public static File appendWriteRow(List<Object> row, String csvPath) {
        BufferedWriter csvWriter = null;
        File csvFile = null;
        try {
            //先读取旧数据
            CsvReader reader = new CsvReader(csvPath, ',', Charset.forName("GBK")); // 一般用这编码读就可以了
            List<List<Object>> dataList = new ArrayList<List<Object>>();
            while (reader.readRecord()) { // 逐行读入除表头的数据
                List<Object> line = new ArrayList<Object>();
                for (String str : reader.getValues()) {
                    line.add(str);
                }
                dataList.add(line);
            }
            dataList.add(row);

            //再重新写入
            csvFile = new File(csvPath);
            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            // 写入数据
            for (List<Object> line : dataList) {
                for (Object data : line) {
                    StringBuffer sb = new StringBuffer();
                    String rowStr = sb.append("\"").append(data).append("\",").toString();
                    csvWriter.write(rowStr);
                }
                csvWriter.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return csvFile;
    }

}
