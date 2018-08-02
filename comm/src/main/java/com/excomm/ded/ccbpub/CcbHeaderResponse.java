package com.excomm.ded.ccbpub;

/**
 * Created by liang on 2018/7/15.
 */
public class CcbHeaderResponse {

    public String sysTxVrsn;//服务版本号 当前为01
    public String sysMsgLen;//应用报文长度
    public String sysRecvTime;//服务接受时间
    public String sysRespTime;//服务响应时间
    public String sysPkgStsType;//报文状态类型 00-请求报文  01-应答报文
    public String sysTxStatus;//服务状态 00 成功、 01-失败、 02-不确定
    public String sysRespCode;//服务响应码
    public String sysRespDescLen;//服务响应描述长度
    public String sysRespDesc;//服务响应描述
    public String sysEvtTraceId;//全局事件跟踪号
    public String sysSndSerialNo;//子交易序号
    public String txLogNo;//账务交易流水号
    public String totalPage;//多页查询总页数
    public String totalRec;//多页查询总笔数
    public String currTotalPage;//多页查询当前页数
    public String currTotalRec;//多页查询当前笔数
    public String stsTraceId;//多页查询状态跟踪
    public String ittpartyJrnlNo;//发起方流水号
    public String sysTxCode;//服务名
    public String chnlCustNo;//电子银行合约编号



}
