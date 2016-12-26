package cn.cgx.summer.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils2
{

    /**
     * 123456加密后是：123456:E10ADC3949BA59ABBE56E057F20F883E
     */

    /** * 16进制字符集 */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /** * 指定算法为MD5的MessageDigest */
    private static MessageDigest messageDigest = null;

    /** * 初始化messageDigest的加密算法为MD5 */
    static
    {
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * * 获取文件的MD5值
     * @param file 目标文件
     * @return MD5字符串
     */
    public static String getFileMD5String(File file)
    {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try
        {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);
            ret = bytesToHex(messageDigest.digest());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (ch != null)
            {
                try
                {
                    ch.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static String getFileMD5String(String fileName)
    {
        return getFileMD5String(new File(fileName));
    }

    public static String getMD5String(String str)
    {

        return getMD5String(str.getBytes());
    }

    public static String getMD5String(byte[] bytes)
    {
        messageDigest.update(bytes);
        return bytesToHex(messageDigest.digest());
    }

    private static String bytesToHex(byte bytes[])
    {
        return bytesToHex(bytes, 0, bytes.length);

    }

    private static String bytesToHex(byte bytes[], int start, int end)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++)
        {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();

    }

    private static String byteToHex(byte bt)
    {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
}