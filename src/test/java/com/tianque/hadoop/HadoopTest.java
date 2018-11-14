package com.tianque.hadoop;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description
 * @auther hzh
 * @create 2018-11-14 10:22
 */
public class HadoopTest {

    public static void main(String[] args) {
        putFile2HDFS2("D:\\applicationContext-production.xml", "/hzh5.xml");


    }


    /**
     * 生成文件系统FileSystem
     *
     * @return
     */
    public static FileSystem getHadoopFileSystem() {
        Configuration configuration = new Configuration();
        //主节点namenode访问地址
        configuration.set("fs.defaultFS", "hdfs://hadoop1:8020");
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }


    /**
     * 关闭
     *
     * @param fileSystem
     */
    public static void closeHadoopFileSystem(FileSystem fileSystem) {
        if (fileSystem != null) {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean mkdirs(String path) {
        boolean result = false;
        FileSystem fileSystem = getHadoopFileSystem();
        try {
            result = fileSystem.mkdirs(new Path(path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeHadoopFileSystem(fileSystem);
        }
        return result;
    }


    /**
     * 创建文件并写入内容
     *
     * @param path
     * @param content 写入的内容
     * @return
     */
    public static boolean createFile(String path, String content) {
        boolean result = false;
        FileSystem fileSystem = getHadoopFileSystem();
        try {
            FSDataOutputStream outputStream = fileSystem.create(new Path(path));
            outputStream.writeUTF(content);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeHadoopFileSystem(fileSystem);
        }
        return result;
    }

    /**
     * @param fromLocal 当时目录时，会把该目录及其里面的文件全部上传
     * @param toHDFS    可以目录可以是文件
     */
    public static void putFile2HDFS(String fromLocal, String toHDFS) {
        FileSystem fileSystem = getHadoopFileSystem();
        try {
            fileSystem.copyFromLocalFile(new Path(fromLocal), new Path(toHDFS));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeHadoopFileSystem(fileSystem);
        }
    }

    /**
     * 通过输入输出流
     *
     * @param fromLocal
     * @param toHDFS
     */
    public static void putFile2HDFS2(String fromLocal, String toHDFS) {
        FileSystem fileSystem = getHadoopFileSystem();
        try {
            FSDataOutputStream outputStream = fileSystem.create(new Path(toHDFS));
            FileInputStream inputStream = new FileInputStream(fromLocal);
            IOUtils.copyBytes(inputStream,outputStream,1024*4,true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeHadoopFileSystem(fileSystem);
        }
    }


}
