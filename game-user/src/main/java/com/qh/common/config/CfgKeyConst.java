package com.qh.common.config;

/**
 * @ClassName ConfigKeyConstant
 * @Description 系统级别的配置常量key
 * @author chenyuezhi
 * @Date 2017年10月30日 下午4:33:16
 * @version 1.0.0
 */
public class CfgKeyConst {
    /***游戏用户默认密码***/
    public static final String pass_default_gameUser = "pass_default_gameUser";
    /***游戏用户默认状态***/
    public static final String state_default_gameUser = "state_default_gameUser";
    
	/***系统配置参数 ip域名******/
    public static final String ip = "ip";
    /***系统配置文件路径***/
    public static final String payFilePath = "payFilePath";
    /***聚富公钥***/
    public static final String qhPublicKey = "qhPublicKey";
    /***聚富私钥***/
    public static final String qhPrivateKey = "qhPrivateKey";
    /***聚富私钥路径****/
    public static final String privateKeyPath = "privateKeyPath";
    /***聚富商户默认密码***/
    public static final String pass_default_merch = "pass_default_merch";
    /***聚富商户默认状态***/
    public static final String state_default_merch = "state_default_merch";
    /***聚富支付域名****/
    public static final String pay_domain = "pay_domain";
    /***聚富前台回调设置***/
    public static final String pay_return_url = "pay_return_url";
    /***聚富后台通知设置***/
    public static final String pay_notify_url = "pay_notify_url";
    /***聚富支付跳转中间页面***/
    public static final String pay_jump_url = "pay_jump_url";
    /***聚富支付跳转绑卡页面***/
    public static final String pay_card_url = "pay_card_url";
    /***聚富扫码通道跳转扫码页面**********/
    public static final String pay_qr_url = "pay_qr_url";
    /***聚富代付前台回调设置***/
    public static final String pay_acp_return_url = "pay_acp_return_url";
    /***聚富代付后台通知设置***/
    public static final String pay_acp_notify_url = "pay_acp_notify_url";
    /***聚富扫码通道二维码路径***/
    public static final String qr_money_path = "qr_money_path";

    /***使用哪个短信发送平台***/
    public static final String sms_send_type = "sms_send_type";

    /****************************************************短信配置********************************************/

    /***阿里云注册模板编号***/
    public static final String sms_aliy_tmpl_code_reg = "sms_aliy_tmpl_code_reg";
    /***阿里云注册模板内容  未配置sms_aliy_tmpl_code_reg时生效 ***/
    public static final String sms_aliy_tmpl_ctx_default_reg = "sms_aliy_tmpl_ctx_default_reg";
    /***阿里云确认模板编号***/
    public static final String sms_aliy_tmpl_code_confirm = "sms_aliy_tmpl_code_confirm";
    /***阿里云确认模板默认内容  未配置sms_aliy_tmpl_code_confirm时生效 ***/
    public static final String sms_aliy_tmpl_ctx_default_confirm = "sms_aliy_tmpl_ctx_default_confirm";
    /***阿里云通知模板编号***/
    public static final String sms_aliy_tmpl_code_notify = "sms_aliy_tmpl_code_notify";
    /***阿里云确认模板默认内容  未配置sms_aliy_tmpl_code_notify时生效 ***/
    public static final String sms_aliy_tmpl_ctx_default_notify = "sms_aliy_tmpl_ctx_default_notify";

    /***阿里云短信账号***/
    public static final String sms_aliy_accesskey_id = "sms_aliy_accesskey_id";
    /***阿里云短信密码***/
    public static final String sms_aliy_accesskey_secret = "sms_aliy_accesskey_secret";
    /***阿里云短信签名***/
    public static final String sms_aliy_sign_name = "sms_aliy_sign_name";

    /***腾讯云注册模板编号***/
    public static final String sms_tx_tmpl_code_reg = "sms_tx_template_code_reg";
    /***腾讯云注册模板默认内容  未配置sms_tx_tmpl_code_reg时生效 ***/
    public static final String sms_tx_tmpl_ctx_default_reg = "sms_tx_tmpl_ctx_default_reg";
    /***腾讯云确认模板编号***/
    public static final String sms_tx_tmpl_code_confirm = "sms_tx_tmpl_code_confirm";
    /***腾讯云确认模板默认内容  未配置sms_tx_tmpl_code_confirm时生效 ***/
    public static final String sms_tx_tmpl_ctx_default_confirm = "sms_tx_tmpl_ctx_default_confirm";
    /***腾讯云通知模板编号***/
    public static final String sms_tx_tmpl_code_notify = "sms_tx_tmpl_code_notify";
    /***腾讯云通知模板默认内容  未配置sms_tx_tmpl_code_notify时生效***/
    public static final String sms_tx_tmpl_ctx_default_notify = "sms_tx_tmpl_ctx_default_notify";

    /***腾讯云短信appid***/
    public static final String sms_tx_appid = "sms_tx_appid";
    /***腾讯云短信appkey***/
    public static final String sms_tx_appkey = "sms_tx_appkey";
    /***腾讯云短信签名***/
    public static final String sms_tx_sign_name = "sms_tx_sign_name";

    /******************************************************************************************************/

}
