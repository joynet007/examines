package com.exservice.dao.repository;

import com.excomm.GsonUtil;
import com.exservice.dao.repository.annotation.TableId;
import com.exservice.dao.repository.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

/**
 * Created by liang on 2018/7/8.
 */
@Component
public class CrudRepositoryImpl<T> implements CrudRepository<T> {

    private StringBuffer save_ziduan ;
    private StringBuffer save_wenhao;

    private StringBuffer updateZiduan;

    private List<SqlObject> list ;
    private List<SqlObject> listpk ;

    private HashMap<String,Object> map;

    private String tableName = "";
    private String tableID = "";

    public static final String SSTTRR = "class java.lang.String";



    @Resource
    private CrudConnection crudConnection;

    /**
     *
     * @throws SQLException
     */
    public void save(T t) {

        autoPrepare(t);

        String sql = "insert into "+tableName+"("+save_ziduan+")values ("+save_wenhao+")";
        System.out.println(this.getClass().toString()+" -- "+sql);

        Connection connection =crudConnection.getConnection();
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(sql);
            String str = GsonUtil.objTOjson(list);
            System.out.println(this.getClass().toString()+" -- "+"转化成json="+str);

            for(int i=0;i<list.size();i++)
            {
                SqlObject sqlObject = (SqlObject) list.get(i);
                int index = i + 1;
                if(sqlObject.getIndexType().equals(SSTTRR)){
                    ps.setString(index, (String) sqlObject.getIndexValue());
                }else if(sqlObject.getIndexType().equals("Long")){
                    ps.setLong(index, (Long) sqlObject.getIndexValue());

                }else if(sqlObject.getIndexType().equals("long")){
                    ps.setLong(index, (Long) sqlObject.getIndexValue());
                }
            }

            ps.executeUpdate();
        }catch(SQLException eq){
            eq.printStackTrace();
        }finally {
            try {
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }

            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }

    }

    /**
     *
     * @throws SQLException
     */
    public void delete(T t) {

        autoPrepare(t);

        String sql = "delete from "+tableName+" where "+tableID+ "= ?";
        System.out.println(sql);

        Connection connection = crudConnection.getConnection();
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(sql);

            try {

                String pkid = BeanUtils.getProperty(t,tableID);
                ps.setString(1, pkid);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            ps.executeUpdate();
        }catch(SQLException eq){
            eq.printStackTrace();
        }finally {

            try {
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }


    /**
     * @throws SQLException
     */
    public void update(T t){

        autoPrepare(t);

        String sql = "update "+tableName +" set "+ updateZiduan +" where "+tableID+ "= ?";
        System.out.println(sql);

        Connection connection = crudConnection.getConnection();
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(sql);

            for(int i=0;i<list.size();i++)
            {
                SqlObject sqlObject = (SqlObject) list.get(i);
                int index = i + 1;
                if(sqlObject.getIndexType().equals(SSTTRR)){
                    ps.setString(index, (String) sqlObject.getIndexValue());

                    if(sqlObject.getIndexName().equals(tableID)){
                        ps.setString(list.size()+1, (String) sqlObject.getIndexValue());
                    }


                }else if(sqlObject.getIndexType().equals("Long")){
                    ps.setLong(index, (Long) sqlObject.getIndexValue());

                    if(sqlObject.getIndexName().equals(tableID)){
                        ps.setLong(list.size()+1, (Long)sqlObject.getIndexValue());
                    }

                }else if(sqlObject.getIndexType().equals("long")){
                    ps.setLong(index, (Long) sqlObject.getIndexValue());

                    if(sqlObject.getIndexName().equals(tableID)){
                        ps.setLong(list.size()+1, (Long)sqlObject.getIndexValue());
                    }
                }
            }



            ps.executeUpdate();
        }catch(SQLException eq){
            eq.printStackTrace();
        }finally {

            try {
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


    public T findOne(T t)  {
        autoPrepare(t);

        String sql = "select * from "+tableName+" where "+tableID+ "= ?";
        System.out.println(sql);

        Connection connection = crudConnection.getConnection();

        Statement st = null;
        ResultSet rs = null;
        T objectresult = null;

        try{
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){

                Field[] fields = t.getClass().getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {//遍历
                    //得到属性
                    Field field = fields[i];
                    //打开私有访问
                    field.setAccessible(true);
                    //获取字段名称
                    String name = field.getName();
                    Object value = null;
                    field.getType().toString();

                    if(  field.getType().toString().equals(SSTTRR)){
                        value = rs.getString(name);
                    }else if( field.getType().toString().equals("Long")){
                        value = rs.getLong(name);
                    }

                    BeanUtils.setProperty(objectresult,name,value);

                }

            }

        }catch(SQLException ex){

            ex.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {

            try{
                if(rs != null){
                    rs.close();
                }
                if(st != null){
                    st.close();
                }
                if(connection != null){
                    connection.close();
                }

            }catch(SQLException ex){
                ex.printStackTrace();
            }

        }

        return objectresult;

    }

    /**
     *
     * 负责统一的调度
     * @param object
     */
    public void autoPrepare(T object){
        //得到class
        Class cls = object.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        autoPackageString(fields,object);
        autoTableName(cls);
        autoTableID(cls);

    }


    //创建对象
    public void autoPackageString(Field[] fields, T object) {
        save_ziduan = new StringBuffer();
        save_wenhao = new StringBuffer();
        updateZiduan = new StringBuffer();

        list = new ArrayList<SqlObject>();
        listpk = new ArrayList<SqlObject>();

        for (int i = 0; i < fields.length; i++) {//遍历
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取字段名称
            String name = field.getName();
            if(save_ziduan.length() > 0 ){
                save_ziduan.append(","+name);
                save_wenhao.append(", ?");

                updateZiduan.append( " , "+name + " = ?" );
            }else{
                save_ziduan.append(name);
                save_wenhao.append("?");

                updateZiduan.append( name + " = ?" );
            }

            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            SqlObject sqlObject = new SqlObject();
            sqlObject.setIndexName(name);
            sqlObject.setIndexValue(value);
            sqlObject.setIndexType(field.getType()+"");

            System.out.println("sqlObject==============="+sqlObject.toString());

            list.add(sqlObject);

        }

    }


    /**
     * 获取对象的注解，并获取注解的表名
     * @param cls
     */
    public void autoTableName(Class cls){
        TableName tablenameAn = (TableName) cls.getAnnotation(TableName.class);
        tableName = tablenameAn.value();
    }

    /**
     * 获取对象的注解，并获取注解的主键 名称
     * @param cls
     * @return
     */
    public void autoTableID(Class cls){
        Field[] fields = cls.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Field f = fields[i];
            TableId tableidAn = (TableId)f.getAnnotation(TableId.class);
            if(tableidAn!=null){
                tableID = tableidAn.value();
            }
        }
    }



    public static CrudRepositoryImpl build()
    {
        CrudRepositoryImpl crudRepositoryImpl = new CrudRepositoryImpl();
        return  crudRepositoryImpl;
    }


    /**
     * 创建一个sqlobjct 主要存入白字段的名称 、 值 、类型 三个字段信息
     *
     */
    public class SqlObject{

        public String indexName;
        public Object indexValue;
        public String indexType;

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }

        public Object getIndexValue() {
            return indexValue;
        }

        public void setIndexValue(Object indexValue) {
            this.indexValue = indexValue;
        }

        public String getIndexType() {
            return indexType;
        }

        public void setIndexType(String indexType) {
            this.indexType = indexType;
        }
    }

}
