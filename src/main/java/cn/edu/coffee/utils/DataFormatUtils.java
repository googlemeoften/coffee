package cn.edu.coffee.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by HeYong on 2017/3/27.
 */
public class DataFormatUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatDetial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Random random = new Random();
    private static final int min = 9999999;
    private static final int max = 99999999;

    private DataFormatUtils() {

    }

    public static String getRandomStr() {
        int s = random.nextInt(max) % (max - min + 1) + min;
        String extendId = DataFormatUtils.getDataStr() + s;
        return extendId;
    }

    public static String getDataStr() {

        return format.format(new Date()).toString().replace("-", "");
    }

    public static String getData() {
        return format.format(new Date());
    }
}
