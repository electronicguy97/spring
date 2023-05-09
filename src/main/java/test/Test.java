package test;


public class Test {
	// ... 가변사이즈 매개변수
	static void print(String... arr) {
		for(String str : arr) {
			System.out.println(str);
		}
	}
	public static void main(String[] args) {
//		print("2022");
//		print("2022","04");
		print("2022","04","22");
	}
}
