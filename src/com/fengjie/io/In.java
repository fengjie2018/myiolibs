package com.fengjie.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.Socket;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fengjie.stdio.Stdout;

/**
 *  这个类提供了从标准输入，文件，网页和套接字读取数字，字符串的方法
 *  读取标记会丢弃空白，读取一行会丢弃换行符
 *  
 *  语系：简体中文-中国
 *  @author fengjie2018@qqcom
 *  @version 1.0 2018年2月16日
 */
public final class In {
    
    // 编码UTF-8
    private static final String CHARSET = "UTF-8";

    // 语系：简体中文-中国
    private static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;

    // 空白符
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // 空字符
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    // 匹配所有字符
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    //Scanner
    private Scanner scanner;

   /**
     * 从System.in初始化一个输入流
     */
    public In(){
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET);
        scanner.useLocale(LOCALE);
    }

   /**
     * 从socket初始化一个输入流
     * @param  socket 套接字
     * @throws IllegalArgumentException 如果不能读取套接字，或套接字为null则抛出异常
     */
    public In(Socket socket) {
        if (socket == null) throw new IllegalArgumentException("套接字为 null");
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("无法打开" + socket, ioe);
        }
    }

   /**
     * 用一个URL初始化输入流
     * @param  url URL
     * @throws IllegalArgumentException 如果无法打开URL或URL为null，则抛出异常
     */
    public In(URL url) {
        if (url == null) throw new IllegalArgumentException("url为null");
        try {
            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is), CHARSET);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("无法打开" + url, ioe);
        }
    }

   /**
     * 用一个文件初始化输入流
     * @param  file 传入文件
     * @throws IllegalArgumentException 如果未能打开文件或文件为null
     */
    public In(File file) {
        if (file == null) throw new IllegalArgumentException("文件为null");
        try {
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("无法打开" + file, ioe);
        }
    }

   /**
     * 从一个字符串初始化输入流
     * @param name String
     * @throws IllegalArgumentException 如果无法打开或为null则抛出异常
     */
    public In(String name) {
        if (name == null) throw new IllegalArgumentException("参数为 null");
        try {
            // 首先尝试从本地文件系统读取
            File file = new File(name);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), CHARSET);
                scanner.useLocale(LOCALE);
                return;
            }

            // getResource查找指定的资源
            URL url = getClass().getResource(name);

            //我暂时无法理解这是在干什么
            //不止从当前类，还从类加载器查找资源
            // try this as well
            if (url == null) {
                url = getClass().getClassLoader().getResource(name);
            }

            // 从网络加载
            if (url == null) {
                url = new URL(name);
            }
            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is), CHARSET);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("未能打开" + name, ioe);
        }
    }

    /**
     * 用给定的Scanner初始化输入流
     * @param  scanner scanner
     * @throws IllegalArgumentException 如果scanner为null则抛出异常
     */
    public In(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("scanner参数为null");
        this.scanner = scanner;
    }

    /**
     * 判断输入流是否存在
     * @return 输入流不为null则返回true
     */
    public boolean exists()  {
        return scanner != null;
    }

    /**
     * 如果输入流为空就返回true，空白符视为空
     * @return boolean
     */
    public boolean isEmpty() {
        return !scanner.hasNext();
    }

   /**
     * 如果输入流还有下一行包括空行就返回true
     * @return boolean
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * 如果输入流还有下一个字符（包括空字符）就返回true
     * @return boolean
     */
    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

   /**
     * 读取下一行，忽略换行符
     * @return String
     * @throws NoSuchElementException 如果输入流没有下一行，则抛出此异常
     */
    public String readLine() {
    	if(!scanner.hasNextLine())
    		throw new NoSuchElementException("没有下一行！");
        String line;
        line = scanner.nextLine();
        return line;
    }

    /**
     * 读取下一个字符
     * @return char
     * @throws NoSuchElementException 如果输入流为空，则抛出此异常
     */
    public char readChar() {
    	if(!hasNextChar())throw new NoSuchElementException("标准输入流为空！");
        scanner.useDelimiter(EMPTY_PATTERN);
        String ch = scanner.next();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return ch.charAt(0);
    }  

   /**
     * 读取剩余的所有输入
     * @return String
     * @throws NoSuchElementException 如果输入流为空，则抛出此异常
     */
    public String readAll() {
    	if(isEmpty())
    		throw new NoSuchElementException("标准输入流为空！");
        scanner.useDelimiter(EVERYTHING_PATTERN);
        String result=scanner.next();
        scanner.useDelimiter(WHITESPACE_PATTERN); 
        return result;
    }

   /**
     * 读取下一个标记，并解释为String
     * @return String
     * @throws NoSuchElementException 如果输入流为空，则抛出此异常
     */
    public String readString() {
    	if(!scanner.hasNext())
    		throw new NoSuchElementException("标准输入流为空！");
        return scanner.next();
    }

   /**
     * 读取下一个标记并尝试解析为int
     * @return int
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为int，则抛出此异常
     */
    public int readInt() {
    	 if(!scanner.hasNextInt())
    	     throw new NoSuchElementException("标准输入为空或读取的内容无法解析为int！");
    	 return scanner.nextInt();
    }

   /**
     * 读取一个double
     * @return double
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为double，则抛出此异常
     */
    public double readDouble() {
    	if(!scanner.hasNextDouble())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为double！");
        return scanner.nextDouble();
    }

   /**
     * 读取一个float
     * @return float
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为float，则抛出此异常
     */
    public float readFloat() {
    	if(!scanner.hasNextFloat())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为float！");
        return scanner.nextFloat();
    }

   /**
     * 读取一个long
     * @return long
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为long，则抛出此异常
     */
    public long readLong() {
    	if(!scanner.hasNextLong())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为long！");
        return scanner.nextLong();
    }

   /**
     * 读取一个short
     * @return short
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为short，则抛出此异常
     */
    public short readShort() {
    	if(!scanner.hasNextShort())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为short！");
        return scanner.nextShort();
    }

   /**
     * 读取一个字节
     * @return byte
     * @throws NoSuchElementException 如果输入流为空或读取的内容无法解析为byte，则抛出此异常
     */
    public byte readByte() {
    	if(!scanner.hasNextByte())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为byte！");
	    return scanner.nextByte();
    }

    /**
     * 读取并返回一个布尔值
     * @return boolean
     * @throws NoSuchElementException  如果输入流为空，就抛出此异常
     * @throws InputMismatchException  如果不能解释为boolean，就抛出此异常
     */
    public boolean readBoolean() {
    	//Scanner自带的nextBoolean()方法不能识别0和1，不如算法四的精妙
        String token = readString();
        if ("true".equalsIgnoreCase(token))  return true;
        if ("false".equalsIgnoreCase(token)) return false;
        if ("1".equals(token))               return true;
        if ("0".equals(token))               return false;
        throw new InputMismatchException("无法解析为boolean！");
    }

    /**
     * 读取所有标记，以空白为分割解析成字符串数组
     * @return String[]
     */
    public String[] readAllStrings() {
    	//如果原字符串全是空白符，结果字符串数组长度就是0，如果原字符串第0位不是空白符，
    	//split()会在结果数组第0位生成空的“前导子串”
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;
        // 应当忽略这个“前导子串”
        String[] decapitokens = new String[tokens.length-1];
        for (int i = 0; i < tokens.length - 1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }

    /**
     * 读取所有行
     * @return String[]
     */
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * 读取所有内容，解释为int
     * @return int[]
     * @throws InputMismatchException 如果有任何一个标记不能解释为int，就抛出此异常
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    /**
     * 读取所有内容，解释为long
     * @return long[]
     * @throws InputMismatchException 如果有任何一个标记不能解释为long，就抛出此异常
     */
    public long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Long.parseLong(fields[i]);
        return vals;
    }

    /**
     * 读取所有输入，解释为double
     * @return double[]
     * @throws InputMismatchException 如果有任何一个标记不能解释为double，就抛出此异常
     */
    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }

   /**
     * 关闭输入流
     */
    public void close() {
        scanner.close();  
    }

    
   /**
     * 对一些方法进行测试
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        In in;
        String urlName = "https://introcs.cs.princeton.edu/stdlib/InTest.txt";

        // 从网页读取
        Stdout.println("测试readAll()," + urlName);
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            Stdout.println(in.readAll());
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();

        // 从网页每次读取一行
        Stdout.println("测试readLine()， " + urlName);
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                String s = in.readLine();
                Stdout.println(s);
            }
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();

        // 从一个网页每次读取一个字符串
        Stdout.println("测试readString()，  " + urlName);
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                String s = in.readString();
                Stdout.println(s);
            }
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();


        // 从当前目录每次读取一行
        Stdout.println("测试readLine()");
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In("./test.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                Stdout.println(s);
            }
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();

        // 每次读取一个字符
        Stdout.println("测试readChar()");
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In("test.txt");
            while (!in.isEmpty()) {
                char c = in.readChar();
                Stdout.print(c);
            }
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();
        Stdout.println();

        // 基于Windows的测试
        Stdout.println("测试readLine()");
        Stdout.println("---------------------------------------------------------------------------");
        try {
            in = new In("D:\\workspace\\myiolibs\\test.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                Stdout.println(s);
            }
            Stdout.println();
        }
        catch (IllegalArgumentException e) {
            Stdout.println(e);
        }
        Stdout.println();
    }
}