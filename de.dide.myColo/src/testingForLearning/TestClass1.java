package testingForLearning;
public class TestClass1 {

	private enum testEnum { ONE, TWO, THREE };
	
	public static void main(String[] args) {
		
		System.out.println(testEnum.valueOf( "ONE" ).name());
		
		for (testEnum e : testEnum.values()) {
			System.out.println(e.name());
		}
		
	}
	
}
