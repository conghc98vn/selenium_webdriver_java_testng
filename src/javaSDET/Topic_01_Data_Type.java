package javaSDET;

import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void mainS(String[] args) {
		// 1: Khai báo trước - khởi tạo dữ liệu sau
		String fullName; // Kiểu dữ liệu - tên biến
		fullName = "Automation FC"; // Gán dữ liệu = giá trị phù hợp

		// 2: Khai báo + khởi tạo cùng lúc
		String adressName = "123 Lê Lợi";

		// Kiểu dữ liệu nguyên thuỷ: Primitive Type
		// Kí tự: char
		char c = 'A'; // Duy nhất 1 kí tự: dùng dấu nháy đơn

		// Số nguyên: byte short int long
		// Không dấu - Không thập phân
		byte bNumber = 127;

		short sNumber = 32767;

		int iNumber = 12345;

		long lNumber = 234349;

		// Số thực: float double
		// Logic: boolean
		float fNumber = 15.8f;
		double dNumber = 15.8d;

		// Kiểu dữ liệu tham chiếu: Reference Type
		// Chuỗi kí tự: String
		String cityName = "Ho Chi Minh";
		System.out.println(cityName);

		cityName = "Ha Noi";
		System.out.println(cityName);

		// Class
		FirefoxDriver fdriver;

		// Interface
		WebDriver driver;

		// Collection: Set List Queue
		// HashSet/ LinkedHashSet/ TreeSet
		HashSet hashSet = new HashSet<>();

		// Deque/..
		// Map: HashMap/ HashTable

		// Object
		Object number;

		// Array
		String[] students = { "Nguyen Van A", "Nguyen Van B" };
		Integer[] point = { 15, 30, 29 };
	}

}
