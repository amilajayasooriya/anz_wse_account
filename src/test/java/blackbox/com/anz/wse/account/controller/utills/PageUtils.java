package blackbox.com.anz.wse.account.controller.utills;

public class PageUtils {
    public static String getContentString(String pageString) {
        return pageString.substring(pageString.indexOf("\"content\":") + "\"content\":".length(), pageString.indexOf(",\"pageable\":"));
    }
}
