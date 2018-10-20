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

knowledgepoint.edit = function (){

};

// $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});

knowledgepoint.del = function(subid,parentid){
    var url = systemNamePath+'/knowledgepoint/delete/'+subid;
    jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
        if (event) {
            $.ajax({
                url: url,
                success: function (data) {
                    if (data.code == baseutil.mess_succ) {
                        $('#knowledgepoint-tree').tree('reload');
                        var url = systemNamePath + '/knowledgepoint/knowledgepoint-view/' + parentid;
                        $('#knowledgepoint-tree-panel').panel('refresh', url);
                    } else {
                        $.messager.alert('提示', obj.mdesc);
                    }
                }
            });
        } else {
            //nothing
        }
    });
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