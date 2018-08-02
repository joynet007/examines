package com.exservice.pojo.po;

import com.exservice.dao.repository.annotation.TableId;
import com.exservice.dao.repository.annotation.TableName;
import lombok.Data;

/**
 * Created by liang on 2018/7/8.
 */

@TableName(value = "tb_knowledgepoint")
@Data
public class KnowledgePoint {

    @TableId(value = "knowledgepointid")
    public long knowledgepointid;
    public String mtitle;
    public long createtime;
    public String mstatus;


}
