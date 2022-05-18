package com.common.core.sql;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
public class SqlUtil {

    private  static  final  Map<Class<?>, Integer> SPLIT = new HashMap<>();

    static {
        SPLIT.put(Byte.class, 0);
        SPLIT.put(Short.class, 0);
        SPLIT.put(Integer.class, 0);
        SPLIT.put(Float.class, 0);
        SPLIT.put(Double.class, 0);


        SPLIT.put(Character.class, 1);
        SPLIT.put(String.class, 1);

        SPLIT.put(Boolean.class, 2);
    }


    /**
     *
     * 将列表中的元素拼装起来
     *
     * @param data data
     * @return in
     */
    public static String inArrayToString(List data) {

        if(data == null || data.size() == 0) {
            return "''";
        }

        StringBuilder in = new StringBuilder();

        for (Object o : data) {

            // 判断 o 的类型

            // 1.判断是否为基本类型或者 String 类型
            in.append(splitConvert(o.getClass(), o))
                    .append(",");
//            in.append(splitConvert(o.getClass(), o))
//                    .append(",");

        }

        in.deleteCharAt(in.length() - 1);


        return in.toString();
    }


    /**
     *
     * 自定义拼装插入语句
     *
     * @param dataList
     * @return
     */
    public static String insertBatchString(List<?> dataList, Class<?> clazz) {

        // 1.获取数据库表名

        TableName annoTableName = clazz.getAnnotation(TableName.class);

        String tableName = annoTableName.value();

        if(StringUtils.isEmpty(tableName)) {
            tableName = clazz.getSimpleName();
        }

        // 2.获取字段
        List<String> columns = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            // 1.判断是否为 主键

            TableId annoId = field.getAnnotation(TableId.class);
            // 说明为主键
            if(annoId != null) {
               columns.add(annoId.value());
               continue;
            }

            TableField annoTableField = field.getAnnotation(TableField.class);

            columns.add(StringUtils.isEmpty(annoTableField) ? field.getName():
                    annoTableField.value());

        }

        StringBuilder sql = new StringBuilder();

        sql.append("insert into ").append(tableName);
        sql.append(" (");

        sql.append(String.join(",", columns));

        sql.append(" ) ");
        sql.append(" values ");

        dataList.forEach(
                e -> {

                    Field[] field = e.getClass().getDeclaredFields();

                    sql.append(" ( ");

                    for (Field f : field) {

                        try {

                            f.setAccessible(true);


                            sql.append(splitConvert(f.getType(), f.get((e))));
                            sql.append(",");

                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        }

                    }
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(" ) ");
                    sql.append(",");

                }
        );

        sql.deleteCharAt(sql.length() - 1);
        sql.append(" on duplicate key ");

        sql.append(" update ");

        for (String c : columns) {
            sql.append( c ).append( " = values( ").append(c).append(" ) ").append(",");
        }

        sql.deleteCharAt(sql.length() - 1);

        return sql.toString();
    }


    /**
     *
     * 向关联表中插入数据，一般为一对多
     * 如果为一对一不建立联系表，联系表多为 id 建立连接，否则需要自定义注解
     *
     * @param s 表一（1）实体类映射
     * @param t 表二（n） 实体类映射
     * @param data 插入数据
     * @return
     */
    public static String insertAssociationSql(Object s, Object t, String associationTableName, Object... data) {

        TableId sIdAnnotation = s.getClass().getAnnotation(TableId.class);
        String sId = sIdAnnotation.value();


        TableId tIdAnnotation = t.getClass().getAnnotation(TableId.class);
        String tId = tIdAnnotation.value();


        StringBuilder sql = new StringBuilder();


        return sql.toString();


    }



    private static String splitConvert(Class<?> clazz, Object o) {

        if(o == null) {
            return " null ";
        }

        if(SPLIT.get(clazz).equals(0)) {
            return o.toString();
        }else if(SPLIT.get(clazz).equals(1)) {

            return "'" + o.toString() + "'";
        }else if(SPLIT.get(clazz).equals(2)) {

            boolean e = (boolean) o;

            return e ? String.valueOf(1) : String.valueOf(0);
        }else {
            throw new IllegalArgumentException("暂时不支持的类型");
        }

    }
}
