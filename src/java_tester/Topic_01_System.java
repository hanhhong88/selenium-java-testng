package java_tester;

public class Topic_01_System {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		System.out.print(projectPath);
		String osName = System.getProperty("os.name");
		System.out.print(osName);

	}

}
