/**
 * Created by liang on 2017/7/13.
 */


//初始化 分页数据
$(document).ready(function (){
    var moniyear = "";
    var sublevel1 = "";
    var sublevel2 = "";
    var sublevel3 = "";
    var countPath = systemNamePath+"/choicequestion/selectallcount?moniyear="+moniyear+
        "&sublevel1="+sublevel1+"&sublevel2="+sublevel2+"&sublevel3="+sublevel3;
    //分页单独的一个分页类
    pageutil.paginationkk(countPath,'choicequestion_pagination','choicequestionlist', moniyear , sublevel1, sublevel2, sublevel3);

});

var choicequestion = {
    sublevel1:'',sublevel1:'',sublevel1:'',moniname:''
};

choicequestion.add = function(id){
    $("body").append($("<div id='choicequestion_win_add'></div>"));
    var url = systemNamePath+"/choicequestion/add";
    $("#choicequestion_win_add").dialog({
        href:url,
        width:900,
        height:650,
        modal:true,
        title:'新增题目',
        onClose : function() {
            $(this).dialog('destroy');
        }
    });


};

choicequestion.edit = function (){
    var row = $('#choicequestionlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var questionid = row.questionid;

        $("body").append($("<div id='choicequestion_win_edit'></div>"));
        var url = systemNamePath+"/choicequestion/edit/"+questionid;
        $("#choicequestion_win_edit").dialog({
            href:url,
            width:900,
            height:650,
            modal:true,
            title:'修改题目',
            onClose : function() {
                $(this).dialog('destroy');
            }
        });
    }
};

// $.ajax({url:systemNamePath+'/userinfo/del/'+usertel,async:false});

choicequestion.del = function(){

    var row = $('#choicequestionlist').datagrid('getSelected');
    if(row == null){
        $.messager.alert('提示','请选取一行数据');
    }else{
        var questionid = row.questionid;

        var url = systemNamePath+'/choicequestion/del/'+questionid;
        jQuery.messager.confirm('提示:','确定删除此节点信息么?',function(event){
            if (event) {
                $.ajax({
                    url: url,
                    success: function (data) {
                        if (data.code == baseutil.mess_succ) {
                            $('#choicequestionlist').datagrid('reload');
                            // $('#choicequestionlist').datagrid('load');
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
 * 点击界面上的按钮查询
 */
choicequestion.tableload=function(){
    var sublevel1 =  $("#cc1_list").combobox("getValue");
    var sublevel2 =  $("#cc2_list").combobox("getValue");
    var sublevel3 =  $("#cc3_list").combobox("getValue");

    var moniyear = $("#moniyear").combobox("getText");

    if(sublevel1 == '' || sublevel1== null){
        sublevel1="";
    }
    if(sublevel2 == '' || sublevel2== null){
        sublevel2="";
    }
    if(sublevel3 == '' || sublevel3== null){
        sublevel3="";
    }
    if(moniyear == '' || moniyear== null){
        moniyear="";
    }

    var countPath = systemNamePath+"/choicequestion/selectallcount?moniyear="+moniyear+
        "&sublevel1="+sublevel1+"&sublevel2="+sublevel2+"&sublevel3="+sublevel3;
    //分页单独的一个分页类

    $('#choicequestionlist').datagrid('load',{
        pageNumber: 1,
        pageSize: 20,
        moniyear: moniyear,
        sublevel1: sublevel1,
        sublevel2: sublevel2,
        sublevel3: sublevel3
    });

    pageutil.paginationkk(countPath,'choicequestion_pagination','choicequestionlist', moniyear , sublevel1, sublevel2, sublevel3);

}



/**
 * 定义上面的工具条
 *
 */
choicequestion.toolbar=[{
    text:'新增',
    iconCls:'icon-add',
    handler:choicequestion.add
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        choicequestion.edit();
    }
},'-',{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        choicequestion.del();
    }
},'-',{
    text:'刷新',
    iconCls:'icon-reload',
    handler:function(){
        choicequestion.reload();
    }
}];
