package utils;

import java.util.Random;

public class KeyUtil {

    private KeyUtil(){}

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUnqiueKey(){

        Random random = new Random();


        System.currentTimeMillis();

        //6位随机数
        Integer randomNum = random.nextInt(900000)+100000;
        return String.valueOf(System.currentTimeMillis()+ randomNum);
    }
}
