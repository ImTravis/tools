package com.tools.Utils;


import com.tools.redis.utils.RedisUtils;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CSVUtils {
    @Autowired
    RedisUtils redisUtil;

    public static List<Object> csvReadOperation(String Str) throws IOException {
        File file = new File(Str);
        CsvReader csvReader = new CsvReader();
        csvReader.setContainsHeader(true);

        CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
        List<Object> list = new ArrayList<>();
        for (CsvRow row : csv.getRows()) {
            list.add(row.getField("deviceid"));
            System.out.println("First column of line: " + row.getField("deviceid"));
        }
        return list;
    }

    public void startCsv()  {
        try{
            List<Object> hadDatasDeviceId = csvReadOperation("E:\\deviceid.csv");
            List<Object> ALlDeviceId = csvReadOperation("E:\\pyddeviceid.csv");

            redisUtil.lSet("hadDatasDeviceId",hadDatasDeviceId,60);
            redisUtil.lSet("aLlDeviceId",ALlDeviceId,60);

            System.out.println( redisUtil.sHasKey("hadDatasDeviceId","12453"));
        }catch (Exception e){
            e.printStackTrace();
        }

//        StringBuffer stringBuffer = new StringBuffer();
//        List<String> terminal = new ArrayList<>();
//        ALlDeviceId.stream().filter(one -> {
//            int k = 0;
//            for (String two : hadDatasDeviceId) {
//                if (two.equals(one)) {
//                    terminal.add(two);
//                }
//
//            }
//            return true;
//        }).collect(Collectors.toList());


//        writeToTxt(new ArrayList(), "E:\\deviceid.txt", stringBuffer.toString());
    }

    public static void writeToTxt(List list, String path, String str) {

        String dir = path.substring(0, path.lastIndexOf("\\"));
        File parent = new File(dir);
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        String enter = "\r\n";
        StringBuffer write;
        try {
            outSTr = new FileOutputStream(new File(path));
            Buff = new BufferedOutputStream(outSTr);
//            for (int i = 0; i < list.size(); i++) {
//                write = new StringBuffer();
//                write.append(list.get(i));
//                write.append(enter);
//                Buff.write(write.toString().getBytes("UTF-8"));
//            }

            write = new StringBuffer();
            write.append(str);
            write.append(enter);
            Buff.write(write.toString().getBytes("UTF-8"));
            Buff.flush();
            Buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
