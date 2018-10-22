//初始化 分页数据
$(document).ready(function (){
    var subjectid = "";
    var countPath = systemNamePath+"/knowledgepoint/selectallcount?subjectid="+subjectid;
    //分页单独的一个分页类
    pageutil.pagination_Knowledgepoint(countPath,'knowledgepoint_pagination','knowledgepointlist', subjectid );

});


var knowledgepoint = {};


knowledgepoint.add = function(id){

    var subjectid =  $("#cc1_list").combobox("getValue");
    if(subjectid == '' || subjectid == undefined){
        $.messager.alert('提示',"请先选择科目！");
        return;
    }
    $("body").append($("<div id='knowledgepoint_win_add'></div>"));
    var url = systemNamePath+"/knowledgepoint/add/";
    $("#knowledgepoint_win_add").dialog({
        href:url,
        width:900,
        height:650,
        modal:true,
        title:'新增知识点',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });


};


/**
 * 新增成功后的 载入页面
 *   在用户选择科目的时候重新显示分页（需要重新计算总条数、总页数）
 *
 */
knowledgepoint.dopageload=function(sjid){
    if(sjid == '' || sjid == null){
        $('#knowledgepointlist').datagrid('load');
    }else{
        var countPath = systemNamePath+"/knowledgepoint/selectallcount?subjectid="+sjid;
        $('#knowledgepointlist').datagrid('load',{
            pageNumber: 1,
            pageSize: 20,
            subjectid: sjid
        });
        pageutil.pagination_Knowledgepoint(countPath,'knowledgepoint_pagination','knowledgepointlist', sjid);
    }
};

/**
 * 刷新页面
 */
knowledgepoint.reload = function (){
    var subjectid =  $("#cc1_list").combobox("getValue");
    if(subjectid == '' || subjectid == undefined){
        knowledgepoint.dopageload('');
    }else{
        knowledgepoint.dopageload(subjectid);
    }
};

/**
 * 删除对象
 * @param subid
 * @param parentid
 */
knowledgepoint.del = function(){

    var row = $('#knowledgepointlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var knowledgepointid = row.knowledgepointid;
        var url = systemNamePath+'/knowledgepoint/delete/'+knowledgepointid;
        jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
            if (event) {
                $.ajax({
                    url: url,
                    success: function (data) {
                        if (data.code == baseutil.mess_succ) {
                            knowledgepoint.dopageload();
                        } else {
                            $.messager.alert('提示', obj.mdesc);
                        }
                    }
                });
            } else {
                //nothing
            }
        });
    }
};


/**
 * 定义上面的工具条
 *
 */
knowledgepoint.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:knowledgepoint.add
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        knowledgepoint.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        knowledgepoint.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        knowledgepoint.reload();
    }
}];