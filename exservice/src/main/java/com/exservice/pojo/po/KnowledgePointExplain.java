package com.exservice.pojo.po;

import com.exservice.dao.repository.annotation.TableId;
import com.exservice.dao.repository.annotation.TableName;
import lombok.Data;

/**
 * Created by liang on 2018/7/8.
 */
@TableName(value = "tb_knowledgepoint_explain")
@Data
public class KnowledgePointExplain {
    @TableId("knowledgepointid")
    public String knowledgepointid;
    public String mexplain;//解析说明
}
