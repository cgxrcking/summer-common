package cn.cgx.summer.cipher;


/**
 * 凯撒移位加密方法
 * @author yl
 *
 */
public class CaesarUtils
{

    /**
     * 加密
     * @param plainStr
     * @return
     */
    public String getCipher(String plainStr)
    {
        return this.caesarCipher(this.transformationStr(plainStr), 6);
    }

    /**
     * 解密
     * @param cipherStr
     * @return
     */
    public String getDecrypt(String cipherStr)
    {
        return this.transformationStr(this.caesarDecrypt(cipherStr, 6));
    }

    /**
     * 位移加密
     * @param plainStr
     * @param n
     * @return
     */
    private String caesarCipher(String plainStr, int n)
    {
        char temp[];
        temp = plainStr.toCharArray();
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] >= 97 && temp[i] <= 122)
            {
                temp[i] = (char) (Math.abs((97 - temp[i] - n) % 26) + 97);
            }
            else if (temp[i] >= 65 && temp[i] <= 90)
            {
                temp[i] = (char) (Math.abs((65 - temp[i] - n) % 26) + 65);
            }
        }
        return new String(temp);
    }

    /**
     * 位移解密
     * @param cipherStr
     * @param n
     * @return
     */
    private String caesarDecrypt(String cipherStr, int n)
    {
        char temp[];
        temp = cipherStr.toCharArray();
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] >= 97 && temp[i] <= 122)
            {
                temp[i] = (char) ((Math.abs(26 - (97 - temp[i] + n)) % 26) + 97);
            }
            else if (temp[i] >= 65 && temp[i] <= 90)
            {
                temp[i] = (char) ((Math.abs(26 - (65 - temp[i] + n)) % 26) + 65);
            }
        }
        return new String(temp);
    }

    /**
     * 替换加密
     * @param plainStr
     * @return
     */
    private String transformationStr(String plainStr)
    {
        char temp[];
        temp = plainStr.toCharArray();
        for (int i = 0; i < temp.length; i++)
        {
            switch (temp[i])
            {
                case '0':
                {
                    temp[i] = 'f';
                    break;
                }
                case '1':
                {
                    temp[i] = 'm';
                    break;
                }
                case '2':
                {
                    temp[i] = 'e';
                    break;
                }
                case '3':
                {
                    temp[i] = 'i';
                    break;
                }
                case '4':
                {
                    temp[i] = 'b';
                    break;
                }
                case '5':
                {
                    temp[i] = 'k';
                    break;
                }
                case '6':
                {
                    temp[i] = 'd';
                    break;
                }
                case '7':
                {
                    temp[i] = 'c';
                    break;
                }
                case '8':
                {
                    temp[i] = 'n';
                    break;
                }
                case '9':
                {
                    temp[i] = 'a';
                    break;
                }
                case 'f':
                {
                    temp[i] = '0';
                    break;
                }
                case 'm':
                {
                    temp[i] = '1';
                    break;
                }
                case 'e':
                {
                    temp[i] = '2';
                    break;
                }
                case 'i':
                {
                    temp[i] = '3';
                    break;
                }
                case 'b':
                {
                    temp[i] = '4';
                    break;
                }
                case 'k':
                {
                    temp[i] = '5';
                    break;
                }
                case 'd':
                {
                    temp[i] = '6';
                    break;
                }
                case 'c':
                {
                    temp[i] = '7';
                    break;
                }
                case 'n':
                {
                    temp[i] = '8';
                    break;
                }
                case 'a':
                {
                    temp[i] = '9';
                    break;
                }
            }
        }
        return new String(temp);
    }

    public static void main(String[] args)
    {
        CaesarUtils caesarUtils = new CaesarUtils();
        String s1 = "ABCDEFGHIGKLMNOPQRSTUVWXYZ1234567890";
        System.out.println("第一次加密:" + caesarUtils.transformationStr(s1));
        System.out.println("第二次加密:" + caesarUtils.getCipher(s1));
        System.out.println("第一次解密:" + caesarUtils.caesarDecrypt(caesarUtils.getCipher(s1), 6));
        System.out.println("第二次解密:" + caesarUtils.getDecrypt(caesarUtils.getCipher(s1)));
        System.out.println("明文:" + s1);
        System.out.println("是否ok:" + s1.equals(caesarUtils.getDecrypt(caesarUtils.getCipher(s1))));
    }
}
