package live.midreamsheep.frame.sioc.util;

public class StringUtil {
    /**
     * 首字母小写
     * */
    public static String firstCharToLowerCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        //判断是否在65-90之间，不在则直接返回
        if (chars[0] < 97 || chars[0] > 122) {
            return str;
        }
        return String.valueOf(chars);
    }
}
