package com.jingbabyadmin.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/csdn?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SQL = "SELECT * FROM ";// 数据库操作
    
    public static final Map<String,String> columnToJavaType=new HashMap<String,String>();

    static {
        try {
            Class.forName(DRIVER);
            columnToJavaType.put("VARCHAR", "String");
            columnToJavaType.put("INT", "Integer");
            columnToJavaType.put("BIGINT", "Long");
            columnToJavaType.put("TIME", "Date");
            columnToJavaType.put("DOUBLE", "double");
            columnToJavaType.put("DATETIME", "Date");
            columnToJavaType.put("TEXT", "String");
            
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }
    public static void main(String[] args) {
        List<String> tableNames = getTableNames();
        getEntity(tableNames);
    }
    
    /**
     * 生成实体类
     * @param tableNames
     */
    public static void getEntity(List<String> tableNames) {
    	System.out.println("tableNames:" + tableNames);
        System.out.println("--------------");
        for (String tableName : tableNames) {
        	System.out.println("表名："+tableName);
        	
        	List<String> names = getColumnNames(tableName);
        	List<String> types = getColumnTypes(tableName);
        	List<String> comments = getColumnComments(tableName);
        	System.out.println("public class "+toClassCase(tableName) + "{");
        	for (int i=0; i<names.size(); i++) {
        		System.out.println("");
        		System.out.println("	// " + comments.get(i));
        		System.out.println("	private " + columnToJavaType.get(types.get(i)) + " " + toCamelCase(names.get(i)) + ";");
        	}
        	for (int i=0; i<names.size(); i++) {
        		System.out.println("");
        		System.out.println("	public void set" + toClassCase(names.get(i)) + "("+ columnToJavaType.get(types.get(i)) +" "+ toCamelCase(names.get(i)) +"){");
        		System.out.println("		this." + toCamelCase(names.get(i)) + " = " + toCamelCase(names.get(i)) + ";");
        		System.out.println("	}");
        		
        		System.out.println("");
        		System.out.println("	public "+columnToJavaType.get(types.get(i))+" get" + toClassCase(names.get(i)) + "(){");
        		System.out.println("		return " + toCamelCase(names.get(i)) + ";");
        		System.out.println("	}");
        	}
        	System.out.println("}");
        	System.out.println("");
        	System.out.println("--------------");
        }
    }
    
    /**
     * 驼峰命名
     * @param str
     * @return
     */
    public static String toCamelCase(String str){
        if(str==null||!str.contains("_"))return str;
        StringBuffer buf=new StringBuffer();
        char[] charArray = str.toCharArray();
        for(int i=0,len=charArray.length;i<len-1;i++){
            if(charArray[i]=='_'&&(charArray[i+1]>='a'&&charArray[i+1]<'z')){
                charArray[i+1]=(char) (charArray[i+1]-32);
            }
            if(charArray[i]!='_')
            buf.append(charArray[i]);
        }
        buf.append(charArray[charArray.length-1]);
        return buf.toString();
    }
    
    /**转换类名
     * @param str
     * @return
     */
    public static String toClassCase(String str){
    	str = str.replace("s_", "");
        String camelCase = toCamelCase(str);
        String substring = camelCase.substring(0,1);
        return camelCase.replaceFirst(substring, substring.toUpperCase());
    }
}