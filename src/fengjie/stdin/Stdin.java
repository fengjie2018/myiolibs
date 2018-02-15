package fengjie.stdin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import fengjie.stdout.*;

/**
 *  这个类提供了从标准输入流读取字符串和数字的方法
 *
 *  可以从标准输入读取一个标记，并把它解析为数字或字符
 *  可以从标准输入读取一串标记，并尝试把它解析为指定的类型并保存在数组中
 *  可以从标准输入中读取一行并丢弃行尾符
 *  
 *  通常，可以用模拟文件尾结束输入
 *  
 *  如果标准输入中没有内容，读取方法就会抛出异常，
 *  这就是说，读取方法绝不返回一个null。
 *  最好每次读取前都检验是否还有东西可读取。
 *  
 *  编码：UTF-8
 *  语言：简体中文-中国
 *
 *  @author fengjie2018@qq.com
 *  @version 1.0 2018年2月15日
 *  @since JDK1.8
 */
public final class Stdin {

    // 编码：UTF-8
    private static final String CHARSET = "UTF-8";

    // 简体中文-中国
    private static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;

    //空白符
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // 空字符
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    // 用来读取所有输入
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
    /*这就是正则表达式，果然简洁灵活*/
    
    //scanner
    private static Scanner scanner;
 
    // 不能创建实例
    private Stdin() {}

   /**
     * 如果标准输入为空就返回true，注意空白符不为空
     * @return boolean
     */
    public static boolean isEmpty() {
        return !scanner.hasNext();
    }

   /**
     * 如果标准输入还有下一行包括空行就返回true
     * @return boolean
     */
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * 如果还有下一个字符（包括空字符）就返回true
     * @return boolean
     */
    public static boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }


   /**
     * 读取下一行，忽略换行符
     * @return String
     * @throws NoSuchElementException 如果标准输入没有下一行，则抛出此异常
     */
    public static String readLine() {
    	if(!scanner.hasNextLine())
    		throw new NoSuchElementException("没有下一行！");
        String line;
        line = scanner.nextLine();
        return line;
    }

    /**
     * 读取下一个字符
     * @return char
     * @throws NoSuchElementException 如果标准输入为空，则抛出此异常
     */
    public static char readChar() {
    	if(!hasNextChar())throw new NoSuchElementException("标准输入流为空！");
        scanner.useDelimiter(EMPTY_PATTERN);
        String ch = scanner.next();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return ch.charAt(0);
    }  

   /**
     * 读取剩余的所有输入
     * @return String
     * @throws NoSuchElementException 如果标准输入为空，则抛出此异常
     */
    public static String readAll() {
    	if(isEmpty())
    		throw new NoSuchElementException("标准输入流为空！");
        scanner.useDelimiter(EVERYTHING_PATTERN);
        String result=scanner.next();
        scanner.useDelimiter(WHITESPACE_PATTERN); 
        return result;
    }


   /**
     * 读取下一个标记，并解释为String
     *
     * @return String
     * @throws NoSuchElementException 如果标准输入为空，则抛出此异常
     */
    public static String readString() {
    	if(!scanner.hasNext())
    		throw new NoSuchElementException("标准输入流为空！");
        return scanner.next();
    }

   /**
     * 读取下一个标记并尝试解析为int
     * @return int
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为int，则抛出此异常
     */
    public static int readInt() {
    	 if(!scanner.hasNextInt())
    	     throw new NoSuchElementException("标准输入为空或读取的内容无法解析为int！");
    	 return scanner.nextInt();
    }

   /**
     * 读取一个double
     * @return double
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为double，则抛出此异常
     */
    public static double readDouble() {
    	if(!scanner.hasNextDouble())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为double！");
        return scanner.nextDouble();
    }

   /**
     * 读取一个float
     * @return float
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为float，则抛出此异常
     */
    public static float readFloat() {
    	if(!scanner.hasNextFloat())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为float！");
        return scanner.nextFloat();
    }

   /**
     * 读取一个long
     * @return long
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为long，则抛出此异常
     */
    public static long readLong() {
    	if(!scanner.hasNextLong())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为long！");
        return scanner.nextLong();
    }

   /**
     * 读取一个short
     * @return short
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为short，则抛出此异常
     */
    public static short readShort() {
    	if(!scanner.hasNextShort())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为short！");
        return scanner.nextShort();
    }

   /**
     * 读取一个字节
     * @return byte
     * @throws NoSuchElementException 如果标准输入为空或读取的内容无法解析为byte，则抛出此异常
     */
    public static byte readByte() {
    	if(!scanner.hasNextByte())
    		throw new NoSuchElementException("标准输入为空或读取的内容无法解析为byte！");
	    return scanner.nextByte();
    }

    /**
     * 读取并返回一个布尔值
     * @return boolean
     * @throws NoSuchElementException  如果标准输入为空，就抛出此异常
     * @throws InputMismatchException  如果不能解释为boolean，就抛出此异常
     */
    public static boolean readBoolean() {
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
    public static String[] readAllStrings() {
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
    public static String[] readAllLines() {
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
    public static int[] readAllInts() {
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
    public static long[] readAllLongs() {
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
    public static double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }
    
    // 初始化scanner
    static {
        resync();
    }

    /**
     * 重新初始化scanner
     */
    private static void resync() {
        scanner=new Scanner(new java.io.BufferedInputStream(System.in), CHARSET);
        scanner.useLocale(LOCALE);
    }

    /**
     * 交互式测试
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        Stdout.print("请输入一个字符串：");
        String s = Stdin.readString();
        Stdout.println("你输入的是： " + s);
        Stdout.println();

        Stdout.println("请输入一个整数：");
        int a = Stdin.readInt();
        Stdout.println("你输入的是：" + a);
        Stdout.println();

        Stdout.print("请输入一个布尔值：");
        boolean b = Stdin.readBoolean();
        Stdout.println("你输入的是：" + b);
        Stdout.println();

        Stdout.print("请输入一个double");
        double c = Stdin.readDouble();
        Stdout.println("你输入的是：" + c);
        Stdout.println();
    }
}
