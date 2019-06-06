/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:26:01
 * @since 0.0.1
 */
public class FileUtils {
    /**
     * 文件名和扩展名的分隔符
     */
    private final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * 读文件
     *
     * @param file    文件
     * @param charset 编码方式
     * @return 文件内容
     * @since 0.0.1
     */
    public static StringBuilder read(File file, String charset) {
        if (file == null || !file.isFile())
            return null;

        StringBuilder content = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            isr = new InputStreamReader(new FileInputStream(file), charset);
            reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!"".equals(content.toString())) {
                    content.append("\r\n");
                }
                content.append(line);
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            IOUtils.close(reader);
            IOUtils.close(isr);
        }
    }

    /**
     * 读文件
     *
     * @param path    文件路径
     * @param charset 编码方式
     * @return 文件内容
     * @since 0.0.1
     */
    public static StringBuilder read(String path, String charset) {
        return StringUtils.isBlank(path) ? null : read(new File(path), charset);
    }

    /**
     * 写文件
     *
     * @param path    文件路径
     * @param content 文件内容
     * @param append  是否向文件中追加内容，{@code true}在文件结尾追加内容，{@code false}覆盖文件内容
     * @since 0.0.1
     */
    public static void write(String path, String content, boolean append) {
        if (StringUtils.isBlank(content))
            return;

        FileWriter fileWriter = null;
        try {
            createFile(path);
            fileWriter = new FileWriter(path, append);
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    /**
     * 写文件
     *
     * @param path     文件路径
     * @param contents 文件内容列表
     * @param append   是否向文件中追加内容，{@code true}在文件结尾追加内容，{@code false}覆盖文件内容
     * @since 0.0.1
     */
    public static void write(String path, List<String> contents, boolean append) {
        if (CollectionUtils.isEmpty(contents))
            return;

        FileWriter fileWriter = null;
        try {
            createFile(path);
            fileWriter = new FileWriter(path, append);
            int i = 0;
            for (String line : contents) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    /**
     * 写文件
     *
     * @param file   文件
     * @param stream 输入流
     * @param append 是否向文件中追加内容，{@code true}在文件结尾追加内容，{@code false}覆盖文件内容
     * @since 0.0.1
     */
    public static void write(File file, InputStream stream, boolean append) {
        OutputStream o = null;
        try {
            createFile(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte[] data = new byte[1024];
            int length;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred.", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            IOUtils.close(o);
            IOUtils.close(stream);
        }
    }

    /**
     * 写文件
     *
     * @param path   文件路径
     * @param stream 输入流
     * @param append 是否向文件中追加内容，{@code true}在文件结尾追加内容，{@code false}覆盖文件内容
     * @since 0.0.1
     */
    public static void write(String path, InputStream stream, boolean append) {
        write(StringUtils.isBlank(path) ? null : new File(path), stream, append);
    }

    /**
     * 获取文件名，包含扩展名
     *
     * @param path 文件路径
     * @return 文件名，包含扩展名
     * @since 0.0.1
     */
    public static String getFileName(String path) {
        if (StringUtils.isBlank(path) || path.lastIndexOf(FILE_EXTENSION_SEPARATOR) == -1)
            return null;

        int filePosi = path.lastIndexOf(File.separator);
        return (filePosi == -1) ? path : path.substring(filePosi + 1);
    }

    /**
     * 获取文件名，不包含扩展名
     *
     * @param path 文件路径
     * @return 文件名，不包含扩展名
     * @since 0.0.1
     */
    public static String getFileNameWithoutExtension(String path) {
        String fileName = getFileName(path);
        return fileName == null ? null : fileName.substring(0, fileName.lastIndexOf(FILE_EXTENSION_SEPARATOR));
    }

    /**
     * 获取文件扩展名
     *
     * @param path 文件路径
     * @return 文件扩展名
     * @since 0.0.1
     */
    public static String getFileExtension(String path) {
        if (StringUtils.isBlank(path))
            return null;

        int extensionPosi = path.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        if (extensionPosi == -1)
            return null;

        return (path.lastIndexOf(File.separator) >= extensionPosi) ? null : path.substring(extensionPosi + 1);
    }

    /**
     * 获取文件所在目录
     *
     * @param path 文件路径
     * @return 文件目录
     * @since 0.0.1
     */
    public static String getFolderName(String path) {
        if (StringUtils.isBlank(path))
            return null;

        int filePosi = path.lastIndexOf(File.separator);
        return filePosi == -1 ? null : path.substring(0, filePosi);
    }

    /**
     * 获取文件大小
     *
     * @param path 文件路径
     * @return 返回文件字节数，若文件不存在或不是文件则返回0
     * @since 0.0.1
     */
    public static long getFileSize(String path) {
        if (StringUtils.isBlank(path))
            return 0L;

        File file = new File(path);
        return (file.exists() && file.isFile()) ? file.length() : 0L;
    }

    /**
     * 获取文件的MD5值
     *
     * @param path 文件路径
     * @return 文件MD5值
     * @since 0.0.5
     */
    public static String getFileMD5(String path) {
        if (StringUtils.isBlank(path))
            return null;

        try {
            byte[] buffer = new byte[8192];
            int len;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取文件的Mime类型
     *
     * @param path 文件全路径
     * @return 文件的Mime类型
     * @since 0.0.5
     */
    public static String getFileMimeType(String path) {
        if (StringUtils.isBlank(path))
            return null;

        return URLConnection.getFileNameMap().getContentTypeFor(path);
    }

    /**
     * 获取文件的Mime类型
     *
     * @param file 文件
     * @return 文件的Mime类型
     * @since 0.0.5
     */
    public static String getFileMimeType(File file) {
        if (file == null)
            return null;

        return getFileMimeType(file.getAbsolutePath());
    }

    /**
     * 获取文件最后的修改时间
     *
     * @param file 文件
     * @return 文件最后的修改时间
     * @since 0.0.5
     */
    public static Date getFileLastModifyTime(File file) {
        if (file == null)
            return null;

        return new Date(file.lastModified());
    }

    /**
     * 获取文件最后的修改时间
     *
     * @param path 文件全路径
     * @return 文件最后的修改时间
     * @since 0.0.5
     */
    public static Date getFileLastModifyTime(String path) {
        if (StringUtils.isBlank(path))
            return null;

        return getFileLastModifyTime(new File(path));
    }

    /**
     * 创建文件目录
     *
     * @param directory 文件目录
     * @return {@code true} 创建成功；{@code false} 创建失败
     * @since 0.0.1
     */
    public static boolean makeDirs(String directory) {
        if (StringUtils.isBlank(directory))
            return false;

        File folder = new File(directory);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @return {@code true} 创建成功；{@code false} 创建失败
     * @since 0.0.1
     */
    public static boolean createFile(String path) {
        if (StringUtils.isBlank(path))
            return false;

        File file = new File(path);
        try {
            file.getParentFile().mkdirs();
            return (file.exists() && file.isFile()) || file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param path 文件路径
     * @return {@code true} 文件存在；{@code false} 文件不存在
     * @since 0.0.1
     */
    public static boolean isFileExist(String path) {
        if (StringUtils.isBlank(path))
            return false;

        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     * 判断文件目录是否存在
     *
     * @param directory 文件目录
     * @return {@code true} 文件目录存在；{@code false} 文件目录不存在
     * @since 0.0.1
     */
    public static boolean isFolderExist(String directory) {
        if (StringUtils.isBlank(directory))
            return false;

        File folder = new File(directory);
        return folder.exists() && folder.isDirectory();
    }

    /**
     * 删除文件
     * <ul>
     * <li>{@code paths}是文件路径，则删除该文件</li>
     * <li>{@code paths}是文件目录，则删除该目录及其子目录下所有文件</li>
     * </ul>
     *
     * @param paths 文件路径
     * @since 0.0.1
     */
    public static void delete(String... paths) {
        if (paths == null)
            return;

        File file;
        for (String path : paths) {
            if (StringUtils.isBlank(path))
                break;
            file = new File(path);
            if (!file.exists())
                break;
            if (file.isFile()) {// 是文件
                file.delete();
            } else if (file.isDirectory()) {// 是目录
                for (File f : file.listFiles()) {
                    if (f.isFile())
                        f.delete();
                    else if (f.isDirectory())
                        delete(f.getAbsolutePath());
                }
            }
            file.delete();
        }
    }

    /**
     * 删除文件
     * <ul>
     * <li>{@code files}是文件，则删除该文件</li>
     * <li>{@code files}是文件目录，则删除该目录及其子目录下所有文件</li>
     * </ul>
     *
     * @param files 文件
     * @since 0.0.1
     */
    public static void delete(File... files) {
        if (files == null)
            return;

        for (File file : files) {
            if (file == null || !file.exists())
                break;
            if (file.isFile()) {// 是文件
                file.delete();
            } else if (file.isDirectory()) {// 是目录
                for (File f : file.listFiles()) {
                    if (f.isFile())
                        f.delete();
                    else if (f.isDirectory())
                        delete(f);
                }
            }
            file.delete();
        }
    }

    /**
     * 快速删除大文件
     *
     * @param file 待处理文件
     * @since 0.0.5
     */
    public static void deleteBigFile(File file) {
        if (file != null) {
            clean(file);
            file.delete();
        }
    }

    /**
     * 快速删除大文件
     *
     * @param path 待处理文件全路径
     * @since 0.0.5
     */
    public static void deleteBigFile(String path) {
        if (StringUtils.isNotBlank(path)) {
            deleteBigFile(new File(path));
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @since 0.0.1
     */
    public static void copyFile(File sourceFile, File targetFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            in = fis.getChannel();// 得到对应的文件通道
            out = fos.getChannel();// 得到对应的文件通道

            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            // 关闭流
            IOUtils.close(fis);
            IOUtils.close(in);
            IOUtils.close(fos);
            IOUtils.close(out);
        }
    }

    /**
     * 复制文件
     *
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件路径
     * @since 0.0.1
     */
    public static void copyFile(String sourcePath, String targetPath) {
        copyFile(new File(sourcePath), new File(targetPath));
    }

    /**
     * 复制文件夹
     *
     * @param sourceDir 源文件
     * @param targetDir 目标文件
     * @since 0.0.1
     */
    public static void copyDir(File sourceDir, File targetDir) {
        if (!sourceDir.exists())
            return;
        // 新建目标目录
        targetDir.mkdirs();
        // 获取源文件夹下的文件或目录
        File[] files = sourceDir.listFiles();
        if (CollectionUtils.isEmpty(files))
            return;

        for (File file : files) {
            if (file.isFile()) {
                copyFile(file, new File(targetDir, file.getName()));
            } else if (file.isDirectory()) {
                copyDir(new File(sourceDir, file.getName()), new File(targetDir, file.getName()));
            }
        }
    }

    /**
     * 复制文件夹
     *
     * @param sourceDirPath 源文件目录
     * @param targetDirPath 目标文件目录
     * @since 0.0.1
     */
    public static void copyDir(String sourceDirPath, String targetDirPath) {
        copyDir(new File(sourceDirPath), new File(targetDirPath));
    }

    /**
     * 移动文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @since 0.0.1
     */
    public static void move(File sourceFile, File targetFile) {
        sourceFile.renameTo(targetFile);
    }

    /**
     * 移动文件
     *
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件目录
     * @since 0.0.1
     */
    public static void move(String sourcePath, String targetPath) {
        if (StringUtils.isBlank(sourcePath) || StringUtils.isBlank(targetPath))
            return;
        move(new File(sourcePath), new File(targetPath));
    }

    /**
     * 清空文件
     *
     * @param file 待处理文件
     * @since 0.0.5
     */
    public static void clean(File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred.", e);
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    /**
     * 清空文件
     *
     * @param path 待处理文件全路径
     * @since 0.0.5
     */
    public static void clean(String path) {
        if (StringUtils.isBlank(path))
            return;

        clean(new File(path));
    }
}
