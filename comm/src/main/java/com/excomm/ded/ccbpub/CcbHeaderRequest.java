package com.excomm.ded.ccbpub;

/**
 * Created by liang on 2018/7/15.
 */

public class CcbHeaderRequest {

    public String sysTxCode;//服务名
    public String sysMsgLe;//应用报文长度
    public String sysReqTime;//发起交易时间
    public String sysTxVrsn;//服务版本号

    public String txnDt;//交易日期
    public String txnTm;//交易时间

    public String txnStffId;//交易人员编号
    public String multiTeancyId;//交易人员编号
    public String lngId;//语言标识

    public String recInPage;//多页查询每页笔数
    public String pageJump;//多页查询跳转页码


    public String sTsTraceId;//状态跟踪号
    public String cHnlCustNo;//电子银行合约编号
    public String iTtpartyJrnlNo;//发起方流水号
    public String tXnIttIpAdr;//交易发起方IP地址




}
