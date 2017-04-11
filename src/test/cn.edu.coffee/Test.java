import cn.edu.coffee.utils.DataFormatUtils;
import cn.edu.coffee.utils.MailUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by HeYong on 2017/3/27.
 */
public class Test {
    public static void main(String[] args) throws IOException, MessagingException {
//       String data =  DataFormatUtils.getDataStr();
//        String phone = "1582321234";
//        boolean isTrue = phone.matches("(\\d+-)?(\\d{4}-?\\d{7}|\\d{3}-?\\d{8}|^\\d{7,8})(-\\d+)?");
//        System.out.println(isTrue);
//
//        String originalName = "dv.as.jpg";
//        int index = originalName.lastIndexOf(".");
//        System.out.println(originalName.substring(index));

        HashMap<String, Object> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        MailUtils.sendMail("结算任务", map, 100);
        System.out.println("========================");
    }
}
