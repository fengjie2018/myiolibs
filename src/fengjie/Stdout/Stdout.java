package fengjie.Stdout;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * 这个类提供打印数字和字符串到标准输出的方法。 编码：UTF-8 语系：zh-CN
 * 在学习《算法》第四版时，我决定仿照书中的代码编写自己的标准输出库，一则练手，二则自用。
 *
 * @author fengjie2018@qq.com
 * @version 1.0
 * @since JDK1.8
 */
public final class Stdout {

	// 字符编码为UTF-8
	private static final String CHARSET = "UTF-8";

	// 中国大陆
	private static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;

	// 输出
	private static PrintWriter out;

	// 最先被执行
	static {
		try {
			out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET), true);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}

	// 禁止创建实例
	private Stdout() {
	}

	/**
	 * 关闭输入
	 */
	public static void close() {
		out.close();
	}

	/**
	 * 换行
	 */
	public static void println() {
		out.println();
	}

	/**
	 * 打印一个Object并换行
	 *
	 * @param x
	 */
	public static void println(Object x) {
		out.println(x);
	}

	/**
	 * 打印一个boolean并换行
	 *
	 * @param x
	 */
	public static void println(boolean x) {
		out.println(x);
	}

	/**
	 * 打印一个char并换行
	 *
	 * @param x
	 */
	public static void println(char x) {
		out.println(x);
	}

	/**
	 * 打印一个double并换行
	 *
	 * @param x
	 */
	public static void println(double x) {
		out.println(x);
	}

	/**
	 * 打印一个float并换行
	 *
	 * @param x
	 */
	public static void println(float x) {
		out.println(x);
	}

	/**
	 * 打印一个int并换行
	 *
	 * @param x
	 */
	public static void println(int x) {
		out.println(x);
	}

	/**
	 * 打印一个long并换行
	 *
	 * @param x 
	 */
	public static void println(long x) {
		out.println(x);
	}

	/**
	 * 打印一个short并换行
	 *
	 * @param x
	 */
	public static void println(short x) {
		out.println(x);
	}

	/**
	 * 打印一个byte并换行
	 *
	 * @param x
	 */
	public static void println(byte x) {
		out.println(x);
	}

	/**
	 * 刷新标准输出流
	 */
	public static void print() {
		out.flush();
	}

	/**
	 * 打印一个Object并刷新
	 * 
	 * @param x
	 */
	public static void print(Object x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个boolean并刷新
	 * 
	 * @param x
	 */
	public static void print(boolean x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个char并刷新
	 * 
	 * @param x
	 */
	public static void print(char x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个double并刷新
	 * 
	 * @param x
	 */
	public static void print(double x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个float并刷新
	 * 
	 * @param x
	 */
	public static void print(float x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个int并刷新
	 * 
	 * @param x
	 */
	public static void print(int x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个long并刷新
	 * 
	 * @param x
	 */
	public static void print(long x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个short并刷新
	 * @param x
	 */
	public static void print(short x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 打印一个byte并刷新
	 * @param x
	 */
	public static void print(byte x) {
		out.print(x);
		out.flush();
	}

	/**
	 * 用指定的格式字符串和参数打印
	 *
	 * @param format 格式字符串
     *
	 * @param args 对应格式串的参数
	 */
	public static void printf(String format, Object... args) {
		out.printf(LOCALE, format, args);
		out.flush();
	}

	/**
	 * 用指定的格式字符串和参数打印
	 * 
	 * @param locale 语系
	 *
	 * @param format 格式字符串
     *
	 * @param args 对应格式串的参数
	 */
	public static void printf(Locale locale, String format, Object... args) {
		out.printf(locale, format, args);
		out.flush();
	}

	/**
	 * 对一些方法进行单元测试
	 *
	 * @param args 命令行参数
	 */
	public static void main(String[] args) {

		Stdout.println("Test");
		Stdout.println("你好啊，我的朋友!");
		Stdout.println(17);
		Stdout.println(true);
		Stdout.printf("%.6f\n", 1.0 / 3.0);
	}

}
