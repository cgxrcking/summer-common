package cn.cgx.summer.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Random;


/**
 * 数据处理工具类
 * @author Administrator
 *
 */
public class DataUtils
{

    /**
     * 保留两位小数
     * @param number
     * @return
     */
    public static String format2Decimal(Double number)
    {
        DecimalFormat df = new java.text.DecimalFormat("##0.00");
        return df.format(number);
    }

    /**
     * 计算x的y次方
     * @param x
     * @param y
     * @return
     */
    public static String power(int x, int y)
    {
        int[] text = new int[1000];
        text[0] = 1;
        for (int i = 0; i < y; i++)
        {
            /**底数与数组中的每一位整数相乘**/
            for (int j = 0; j < text.length; j++)
            {
                text[j] *= x;
            }
            /**进位**/
            for (int j = 0; j < text.length - 1; j++)
            {
                text[j + 1] += text[j] / 10;
                text[j] = text[j] % 10;
            }
        }
        StringBuffer buffer = new StringBuffer();
        int temp = 0;
        for (int i = text.length - 1; i >= 0; i--)
        {
            if (text[i] != 0)
            {
                temp = i;
                break;
            }
        }
        for (int i = temp; i >= 0; i--)
        {
            buffer.append(text[i]);
        }
        return buffer.toString();
    }

    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object)
    {
        if (object == null)
        {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            return null;
        }
        finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                }
                catch (IOException e)
                {
                	e.printStackTrace();
                }
            }
            if (baos != null)
            {
                try
                {
                    baos.close();
                }
                catch (IOException e)
                {
                	e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes)
    {
        if (bytes == null)
        {
            return null;
        }
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            return null;
        }
        finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                }
                catch (IOException e)
                {
                	e.printStackTrace();
                }
            }
            if (bais != null)
            {
                try
                {
                    bais.close();
                }
                catch (IOException e)
                {
                	e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取随机数
     * @param nums  随机数位数
     * @return
     */
    public static String getRandom(int nums)
    {
        String result = "";
        Random random = new Random();
        for (int i = 0; i < nums; i++)
        {
            result += random.nextInt(10);
        }
        return result;
    }

    /**
     * 获取转发器mac地址的散列值
     * 去掉冒号
     * 转大写
     * 按1024进行散列
     * @param mac   转发器mac地址
     * @return
     */
    public static int getMacHash(String mac)
    {
        mac = mac.replaceAll(":", "").toUpperCase();
        return Math.abs(mac.hashCode() % 1024);
    }

    /**
     * 获取UUID的校验位
     * @param uuid
     * @return
     * @throws Exception
     */
    public static String getUUIDCheckBit(String uuid) throws Exception
    {
        if (uuid == null || uuid.length() != 32)
        {
            throw new Exception("非法的字符串");
        }
        int sum = 0;
        for (int i = 0; i < 32; i++)
        {
            sum += uuid.charAt(i);
        }
        sum = sum % 36;
        if (sum <= 9)
        {
            sum += 48;
        }
        else
        {
            sum += 55;
        }
        return String.valueOf((char) sum);
    }
}
