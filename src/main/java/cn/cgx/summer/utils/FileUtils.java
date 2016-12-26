package cn.cgx.summer.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import cn.cgx.summer.cipher.Md5Utils2;

/**
 * 文件处理工具类
 * @author frenn
 *
 */
public class FileUtils
{

    /**
     * 创建本地目录
     * @param path  目录
     * @return      true-成功 false-失败
     */
    public static boolean createLocalPath(String path)
    {
        java.io.File f = new java.io.File(path);
        if (f.exists())
        {
            return true;
        }
        else if (f.mkdirs())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 读取文件
     * @param in
     * @return
     * @throws IOException
     */
    public static String readInfoFromFile(InputStream in) throws IOException
    {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String temp = null;
        while ((temp = reader.readLine()) != null)
        {
            sb.append(temp + "\n");
        }
        return sb.toString();
    }

    /**
     * 复制文件
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean copyFile(File file, String path) throws Exception
    {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try
        {
            char temp = path.charAt(path.length() - 1);
            if (temp != '/' && temp != '\\')
            {
                path += "/";
            }
            createLocalPath(path);
            inBuff = new BufferedInputStream(new FileInputStream(file));
            outBuff = new BufferedOutputStream(new FileOutputStream(path + file.getName()));
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1)
            {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (inBuff != null)
            {
                try
                {
                    inBuff.close();
                }
                catch (IOException e)
                {
                    throw e;
                }
            }
            if (outBuff != null)
            {
                try
                {
                    outBuff.close();
                }
                catch (IOException e)
                {
                    throw e;
                }
            }
        }
    }

    /**
     * 下载文件到本地指定文件夹
     * @param fileUrl 文件访问地址
     * @param targetDir 目标文件夹
     * @throws Exception 
     */
    public static String downloadFile(String fileUrl, String targetDir) throws Exception
    {
        InputStream in = null;
        OutputStream out = null;
        URLConnection urlConnection = null;
        try
        {
            File file = new File(targetDir);
            if (!file.exists())
            {
                System.out.println("下载文件到本地指定文件夹：指定文件夹不存在，系统自动创建此文件夹，targetDir=" + targetDir);
                file.mkdirs();
            }
            int dot = fileUrl.lastIndexOf("/");
            String targetFileName = fileUrl.substring(dot);
            File targetFile = new File(file, targetFileName);
            if (targetFile.exists())
            {
                targetFile.delete();
            }
            URL urlObj = new URL(fileUrl);
            urlConnection = urlObj.openConnection();
            urlConnection.setUseCaches(true);
            in = urlConnection.getInputStream();
            out = new FileOutputStream(targetFile);
            byte buffer[] = new byte[4 * 1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, len);
            }
            out.flush();
            return Md5Utils2.getFileMD5String(targetFile);
        }
        catch (Exception e)
        {
        	System.out.println("下载文件到本地指定文件夹失败，url=" + fileUrl + ",targetDir=" + fileUrl);
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
                System.out.println("下载文件到本地指定文件夹异常时关闭文件流in失败，url=" + fileUrl + ",targetDir=" + fileUrl);
                e.printStackTrace();
            }
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception e)
            {
                System.out.println("下载文件到本地指定文件夹异常时关闭文件流out失败，url=" + fileUrl + ",targetDir=" + fileUrl);
                e.printStackTrace();
            }
        }
    }
}
