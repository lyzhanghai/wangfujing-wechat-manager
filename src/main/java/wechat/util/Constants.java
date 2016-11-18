package wechat.util;

import java.io.InputStream;
import java.util.Properties;

public class Constants {

    public static String ACCESSTOKENURL = "";
    public static String OPENIDUSERINFOURL = "";
    public static String GETOPENIDURL = "";

    static {
        InputStream in = Constants.class.getClassLoader().getResourceAsStream(
                "application.properties");
        Properties p = new Properties();
        try {
            p.load(in);

            ACCESSTOKENURL = p.getProperty("accessTokenUrl");
            OPENIDUSERINFOURL = p.getProperty("OpenidUserinfoUrl");
            GETOPENIDURL = p.getProperty("getOpenidUrl");
        } catch (Exception e) {
        }
    }
}
