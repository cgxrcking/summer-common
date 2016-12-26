package cn.cgx.summer.security;

import java.io.StringWriter;

public class StringSecurityUtils
{
    /**
     * 过滤字符串中的特殊字符（只过滤'<'、'>'）
     * @param value 待过滤的值
     * @return      过滤后的值
     */
    public static String filterString(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            return value;
        }
        StringWriter out = new StringWriter(value.length() * 2);

        int sz;
        sz = value.length();
        for (int i = 0; i < sz; i++)
        {
            char ch = value.charAt(i);
            switch (ch)
            {
                case '<':
                    out.write("&#60;");
                    break;
                case '>':
                    out.write("&#62;");
                    break;
                default:
                    out.write(ch);
                    break;
            }
        }
        return out.toString();
    }

}
