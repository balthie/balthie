package org.balthie.demo.jdk.util.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtilDemo2
{
    public static void main(String[] args) throws ZipException, IOException
    {
        String zipFilePath = "D:\\download\\data-syn\\detail_record_partition_backup.tar";  
        File zipFile = new File(zipFilePath);
        ZipFile zip = new ZipFile(zipFile);  
    }
    
    /**
     * 解压zip格式的压缩文件
     * @param zipFileName 压缩文件
     * @param extPlace 解压目录
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void unzip(String zipFileName, String extPlace) throws Exception {
        try {
            ZipFile zipFile = new ZipFile(zipFileName);
            Enumeration e = zipFile.entries();
            ZipEntry zipEntry = null;
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                String entryName = zipEntry.getName();
                String names[] = entryName.split("/");
                int length = names.length;
                String path = extPlace;
                for (int v = 0; v < length; v++) {
                    if (v < length - 1) {
                        path += names[v] + "/";
                        new File(path).mkdir();
                    }else { // 最后一个
                        if (entryName.endsWith("/")) { // 为目录,则创建文件夹
                            new File(extPlace + entryName).mkdir();
                        }else {
                            InputStream in = zipFile.getInputStream(zipEntry);
                            OutputStream os = new FileOutputStream(new File(extPlace +
                            entryName));
                            byte[] buf = new byte[1024];
                            int len;
                            while ( (len = in.read(buf)) > 0) {
                                os.write(buf, 0, len);
                            }
                            in.close();
                            os.close();
                        }
                    }
                }
            }
            zipFile.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * 压缩zip格式的压缩文件
     * @param inputFilename 压缩的文件或文件夹及详细路径
     * @param zipFilename 输出文件名称及详细路径
     * @throws IOException
     */
    public synchronized void zip(String inputFilename, String zipFilename) throws IOException {    
        zip(new File(inputFilename), zipFilename);    
    }    
     
    /**
     * 压缩zip格式的压缩文件
     * @param inputFile 需压缩文件
     * @param zipFilename 输出文件及详细路径
     * @throws IOException
     */
    public synchronized void zip(File inputFile, String zipFilename) throws IOException {    
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));
        try {    
            zip(inputFile, out, "");    
        } catch (IOException e) {    
            throw e;    
        } finally {    
            out.close();    
        }    
    }
    /**
     * 压缩zip格式的压缩文件
     * @param inputFile 需压缩文件
     * @param out 输出压缩文件
     * @param base 结束标识
     * @throws IOException
     */
    @SuppressWarnings("unused")
    private synchronized void zip(File inputFile, ZipOutputStream out, String base) throws IOException {
        if (inputFile.isDirectory()) {    
            File[] inputFiles = inputFile.listFiles();    
            out.putNextEntry(new ZipEntry(base + "/"));    
            base = base.length() == 0 ? "" : base + "/";    
            for (int i = 0; i < inputFiles.length; i++) {    
                zip(inputFiles[i], out, base + inputFiles[i].getName());    
            }
        } else {    
            if (base.length() > 0) {    
                out.putNextEntry(new ZipEntry(base));    
            } else {    
                out.putNextEntry(new ZipEntry(inputFile.getName()));    
            }
            FileInputStream in = new FileInputStream(inputFile);    
            try {    
                int c;    
                byte[] by = new byte[1024];    
                while ((c = in.read(by)) != -1) {    
                    out.write(by, 0, c);    
                }    
            } catch (IOException e) {    
                throw e;    
            } finally {
                in.close();    
            }    
        }    
    }
}
