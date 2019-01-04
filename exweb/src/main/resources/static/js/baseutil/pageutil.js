var pageutil = {};
/**
 * allcountpath : 查询所有条数的url路径
 * page_div : 在list页面的 分页条div
 * page_list : 在list页面的主 list
 */
pageutil.pagination = function( allcountPath, page_pagination , page_list){
    $.ajax({ url: allcountPath, success: function(data){
        $('#'+page_pagination).pagination({
            total:data,
            pageSize:20,
            showPageList:true,
            showRefresh:false,
            //displayMsg: '',
            displayMsg:'当前显示从{from}到{to},共{total}记录',
            pageList: [5,10,20,30,40],
            beforePageText: '第',
            afterPageText: '页    共 {pages} 页',
            onSelectPage:function(pageNumber, pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize
                });

            },onChangePageSize:function(pageNumber,pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize
                });
            },

        });


    }});

}



pageutil.paginationkk = function( allcountPath, page_pagination , page_list , moniyear , sublevel1, sublevel2, sublevel3){
    $.ajax({ url: allcountPath, success: function(data){
        $('#'+page_pagination).pagination({
            total:data,
            pageSize:20,
            showPageList:true,
            showRefresh:false,
            //displayMsg: '',
            displayMsg:'当前显示从{from}到{to},共{total}记录',
            pageList: [5,10,20,30,40],
            beforePageText: '第',
            afterPageText: '页    共 {pages} 页',
            onSelectPage:function(pageNumber, pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize,
                    moniyear: moniyear,
                    sublevel1: sublevel1,
                    sublevel2: sublevel2,
                    sublevel3: sublevel3
                });

            },onChangePageSize:function(pageNumber,pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize,
                    moniyear: moniyear,
                    sublevel1: sublevel1,
                    sublevel2: sublevel2,
                    sublevel3: sublevel3
                });
            },

        });


    }});

}


/**
 * 知识点管理的 分页
 * @param allcountPath  查询总条数的URL
 * @param page_pagination 分页的进度条
 * @param page_list  'knowledgepointlist'
 * @param subjectid
 */
pageutil.pagination_Knowledgepoint = function( allcountPath, page_pagination , page_list , subjectid){
    $.ajax({ url: allcountPath, success: function(data){
        $('#'+page_pagination).pagination({
            total:data,
            pageSize:20,
            showPageList:true,
            showRefresh:false,
            //displayMsg: '',
            displayMsg:'当前显示从{from}到{to},共{total}记录',
            pageList: [5,10,20,30,40],
            beforePageText: '第',
            afterPageText: '页    共 {pages} 页',
            onSelectPage:function(pageNumber, pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize,
                    subjectid: subjectid
                });

            },onChangePageSize:function(pageNumber,pageSize){
                $('#'+page_list).datagrid('load',{
                    pageNumber: pageNumber,
                    pageSize: pageSize,
                    subjectid: subjectid
                });
            },

        });


    }});

}
