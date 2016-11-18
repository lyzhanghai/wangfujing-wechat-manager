package wechat.para;

/**
 * Created by wangxuan on 2016-11-18 0018.
 * 获取用户基本信息（包括UnionID机制）查询参数
 */
public class WechatUserInfoQueryPara {

    /**
     * 调用接口凭证(必需)
     */
    private String access_token;

    /**
     * 普通用户的标识，对当前公众号唯一(必需)
     */
    private String openid;

    /**
     * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语(非必需)
     */
    private String lang;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "WechatUserInfoQueryPara{" +
                "access_token='" + access_token + '\'' +
                ", openid='" + openid + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
