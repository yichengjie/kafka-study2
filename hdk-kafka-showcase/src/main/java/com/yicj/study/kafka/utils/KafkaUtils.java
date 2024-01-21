package com.yicj.study.kafka.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

/**
 * @author yicj
 * @date 2023年04月08日 16:07
 */
public class KafkaUtils {

    /**
     * 将classpath下的文件复制一个临时文件
     * @param classPathFile classpath下的文件
     * @return 临时文件
     */
    public static File copyClassPathTempFile(String classPathFile, String defaultTempFileName) {
        String tempPath = Optional.ofNullable(defaultTempFileName)
                .orElseGet(() -> UUID.randomUUID().toString().replaceAll("-", "") + ".jks")  ;
        File tempFile = new File(tempPath);
        if (!tempFile.exists()){
            try {
                tempFile.createNewFile();
            }catch (IOException e){
                throw new RuntimeException("新建文件【"+tempPath+"】失败!") ;
            }
        }
        Resource resource = new ClassPathResource(classPathFile);
        try(InputStream inputStream = resource.getInputStream();
            OutputStream outputStream = new FileOutputStream(tempFile)) {
            StreamUtils.copy(inputStream, outputStream);
            return tempFile;
        }catch (IOException e){
            throw new RuntimeException("复制的"+classPathFile+"文件不存在！") ;
        }
    }
}
