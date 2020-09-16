package cn.com.zls.ssm.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id ="2021000118645476";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFhHh10OS0y7ATwrPbYqe8DDLoyAla2nOw3/DwS5wfVbxBHoBi+bUuaOcQYFhVK3S6k+kpOM0eeIAXEvcAkyy+QZgnQa8NHZtdbivA7vdf3lG+Y4/+ltznrqqcl+uVqa0ZUFgLj1vWe+Un7KSHD89QVnsFwk9+/vzsh85VsJcwV/sne4kB2tMNMqIo722IEo5yCPzIARSWSkyn4clhINMVnopC3u+ffO9WIRI1NwS82fwWoPlmkgAYDhHXsbqkq1y/BPPjI34NNsbhq/zZyppaRlTwl0MlYZzZ5Cq0fRu3nptpXPBhjZgn+yk4+8zV7Mt6G74tlLsVT9CHml4+8DuxAgMBAAECggEAX0Hcw4muskKkM+T0dvAS5bn6VFPrQLOcWLPc7UO0djKv5fHB/VtUZXEYcXmba+BeHoNKlUteJIAqOswgB3v5LyATUuas4nmeD/F7K+ISAYbW4TOdVzEBGBZoyOImhReSVP4aYtz707F/E5Xk6CtiaFna2YgBOYTopERb9IzRwP3sQT/qhXA863Bc43mC5lXtT6uzKimq7P3uhawBdADF2MMh5FXHiBhwFBI7FEh1Ig5lKgkYzvFpJj658Pe64hqL6gJwxY78hRrBWyHXWdhnEodsWGo83cg4EEH8HrEDH2CzOjfXaA7/wk5wWZO67VPVDveu1pHv7LZ4cT4BD4fxsQKBgQDAXs4ix+IpeZw7PfnI0ixQQpC9OMycr3Jm0WkovpYhVvkcr6+MJ3TKGo8gx99e6EuT3mbRbhfgizFHWl0VSfdo5mfIcfAtOrPOIR85DIjofDKW90UNSKfqRmHSwHWoGzMbEqAFqbvouwhNLe4Udf4ROswj69KJkhZhYKnqXMii5QKBgQCxrjni9s3OhNYD/oq2W5D/TlEDti7nuM3LHEVDxXz633bTYzxgUkUl8nhZ/7/gEl93vT5iP14eFbVMRf1vAPU82pEN8TiPeTucqrKVlSNV8jY90Zn4oN72SMujCm68NuKG65yImsFEMZpRXo3cz7KIMH3A3bJS5XOE0v4KTcds3QKBgGiepsjvoCYiRz9I02HOO99idEkUbOMB39vfI6vPCuMS4+Qf//7fLnsDurd/PBHnj8LZf12C3GV4DdLaaCk90towBF1U6eQPOt5t0Qc7s/GMKg9EZz90Ni4JHQZ7OQG12TNuC61IxDMsFLW0tvhVu1kvevpGNHgxBQ7sfTxCPbOpAoGAcN15S+D2rr7Wq8bfGiNkIaj/8VmixfMwVlro7LngQAP/GRwqWA0qe4ZgSNsaaR2Coy8FF6elA3tKpN64s/Bp13QHPFIEWa7q3QPZWQdZYNjw96fTJIob11o6cKDJ4qkCkC3OSz/ykHzpx9JGTwNoJvaWixLImbvC+5aDZMwvZ8kCgYBPem18eXWaZGiv69ib4KMOiS+wKnzXiB0CUI5LKk1U6+YuUX/f/oe4SMQS5p0mc6wXFvxoVYvdOdG62X25n7EoqDkqNYyKcTsKTJnGCerTPaHhxiB3s2WH5xuEGh4S+LYLe49VIJwMRnXkJrqN6ya08xL5N046JdtZxWGkOyDDjw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
  public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhYR4ddDktMuwE8Kz22KnvAwy6MgJWtpzsN/w8EucH1W8QR6AYvm1LmjnEGBYVSt0upPpKTjNHniAFxL3AJMsvkGYJ0GvDR2bXW4rwO73X95RvmOP/pbc566qnJfrlamtGVBYC49b1nvlJ+ykhw/PUFZ7BcJPfv787IfOVbCXMFf7J3uJAdrTDTKiKO9tiBKOcgj8yAEUlkpMp+HJYSDTFZ6KQt7vn3zvViESNTcEvNn8FqD5ZpIAGA4R17G6pKtcvwTz4yN+DTbG4av82cqaWkZU8JdDJWGc2eQqtH0bt56baVzwYY2YJ/spOPvM1ezLehu+LZS7FU/Qh5pePvA7sQIDAQAB" ;


	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080//orders/afterOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

