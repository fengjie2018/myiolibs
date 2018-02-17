//把数据写入标准输出，文件或套接字
package com.fengjie.io;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

/**
 *  这个类提供了把字符串，数字写入输出流的方法
 *  编码：UTF-8
 *  语系：简体中文-中国
 *  @author fengjie2018@qq.com
 *  @since JDK1.8
 *  @version 1.0 2018年2月16日
 *  
 */
public class Out {

    // 编码：UTF-8
    private static final String CHARSET = "UTF-8";

    // 语系：简体中文-中国
    private static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;

    //Writer
    private PrintWriter out;

    /**
     * 初始化输出流
     * @param os OutputStream
     */
    public Out(OutputStream os) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
            //这个boolean若为true，println(),printf()会刷新输出流
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从标准输出流初始化
     */
    public Out() {
        this(System.out);
    }

    /**
     * 用套接字初始化输出流
     * @param  socket socket
     */
    public Out(Socket socket) {
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用文件初始化输出流
     * @param filename 文件名
     */
    public Out(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输入流
     */
    public void close() {
        out.close();
    }

    /**
     * 换行
     */
    public void println() {
        out.println();
    }

    /**
     * 打印Object并换行
     * @param x object
     */
    public void println(Object x) {
        out.println(x);
    }

    /**
     * 打印布尔并换行
     * @param x boolean
     */
    public void println(boolean x) {
        out.println(x);
    }

    /**
     * 打印char并换行
     * @param x char
     */
    public void println(char x) {
        out.println(x);
    }

    /**
     * 打印double并换行
     * @param x double
     */
    public void println(double x) {
        out.println(x);
    }

    /**
     * 打印float并换行
     * @param x float
     */
    public void println(float x) {
        out.println(x);
    }

    /**
     * 打印int并换行
     * @param x int
     */
    public void println(int x) {
        out.println(x);
    }

    /**
     * 打印long并换行
     * @param x long
     */
    public void println(long x) {
        out.println(x);
    }

    /**
     * 打印byte并换行
     * @param x byte
     */
    public void println(byte x) {
        out.println(x);
    }

    /**
     * 刷新输出流
     */
    public void print() {
        out.flush();
    }

    /**
     * 打印并刷新
     * @param x object
     */
    public void print(Object x) {
        out.print(x);
        out.flush();
    }

    /**
     * 打印并刷新
     * @param x boolean
     */
    public void print(boolean x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新 
     * @param x char
     */
    public void print(char x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新
     * @param x double
     */
    public void print(double x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新
     * @param x float
     */
    public void print(float x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新
     * @param x int
     */
    public void print(int x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新
     * @param x long
     */
    public void print(long x) {
        out.print(x);
        out.flush();
    }

   /**
     * 打印并刷新
     * @param x byte
     */
    public void print(byte x) {
        out.print(x);
        out.flush();
    }

   /**
     * 格式化输出
     * @param format 格式串
     * @param args   格式串对应的内容
     */
    public void printf(String format, Object... args) {
        out.printf(LOCALE, format, args);
    }

   /**
     * 格式化输出
     * @param locale 语系
     * @param format 格式串
     * @param args   格式串对应内容
     */
    public void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
    }


   /**
     * 测试
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Out out;

        // 标准输出流
        out = new Out();
        out.println("测试（Test 1）");
        out.close();

        // 写入文件
        out = new Out("test01.txt");
        out.println("写入Test 2");
        out.close();
    }
}