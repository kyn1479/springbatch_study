package com.kyn.springbatch_study.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 交易状态枚举类
 * @date 2021/2/26
 */
public enum ProcessStatusEnum {
    INIT("INIT", "初始状态，未进行任何处理"),
    PARAM_CHECK_S("PARAM_CHECK_S", "参数验证通过"),
    PARAM_CHECK_F("PARAM_CHECK_F", "参数验证失败"),
    PROCESSING("PROCESSING", "处理中"),
    SENDING("SENDING", "发送中"),
    MESSAGE_ASSEMBLE("MESSAGE_ASSEMBLE", "报文组装中"),
    HAS_SEND("HAS_SEND", "已发送"),
    SEND_FAIL("SEND_FAIL", "发送失败"),
    SEND_FAIL_VERIFY("SEND_FAIL_VERIFY", "发送失败待确认"),
    QUEUEING("QUEUEING", "排队中"),
    HAS_BACK("HAS_BACK", "已返回"),
    RESULT_PARSING("RESULT_PARSING", "结果解析中"),
    HAS_NOTIFY("HAS_NOTIFY", "已通知业务方"),
    BACK_FILE_GENERATING("BACK_FILE_GENERATING", "回盘文件生成中"),
    FILE_BACK("FILE_BACK", "已文件回盘"),
    FINISH("FINISH", "结束"),
    ACCEPT("ACCEPT", "交易已受理"),
    DOWN_HANDING("DOWN_HANDING", "下载处理中"),
    DOWN_SUCCESS("DOWN_SUCCESS", "下载成功"),
    DOWN_FAIL("DOWN_FAIL", "下载失败"),
    DOWN_FAIL_OVERTIMES("DOWN_FAIL_OVERTIMES", "下载失败超过下载次数"),
    FILE_VAL_FAIL("FILE_VAL_FAIL", "文件格式校验失败"),
    FILE_VAL_SUCCESS("FILE_VAL_SUCCESS", "文件格式校验成功"),
    ROUTE_END("ROUTE_END", "路由结束"),
    SPLIT_BATCH_SUCCESS("SPLIT_BATCH_SUCCESS", "分批次处理成功"),
    SPLIT_BATCH_EXCEPTION("SPLIT_BATCH_EXCEPTION", "分批次异常"),
    DATA_SAVE_SUCCESS("DATA_SAVE_SUCCESS", "数据入库成功"),
    DATA_SAVE_FAIL("DATA_SAVE_FAIL", "数据入库异常"),
    REPLY_SUCCESS("REPLY_SUCCESS", "已回盘"),
    REPLY_PART("REPLY_PART", "部分回盘"),
    REPLY_NO("REPLY_NO", "未回盘"),
    REPLY_QUERYING("REPLY_QUERYING", "回盘查询中"),
    UNSIGN("UNSIGN", "已解约"),
    FAIL_QUERY("FAIL_QUERY", "查询失败"),
    FAIL("FAIL", "处理失败"),
    SUCCESS("SUCCESS", "处理成功"),
    PENDING("PENDING", "待定"),
    ;

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>DictEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private ProcessStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return DictEnum
     */
    public static ProcessStatusEnum getByCode(String code) {
        for (ProcessStatusEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<DictEnum>
     */
    public List<ProcessStatusEnum> getAllEnum() {
        List<ProcessStatusEnum> list = new ArrayList<ProcessStatusEnum>();
        for (ProcessStatusEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (ProcessStatusEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}