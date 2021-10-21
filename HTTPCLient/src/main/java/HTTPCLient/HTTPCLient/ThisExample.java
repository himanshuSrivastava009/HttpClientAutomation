package HTTPCLient.HTTPCLient;

public class ThisExample {

	
	public ThisExample() {
	

		System.out.println("This is Constructor call");
	}
	
	public ThisExample(int x)
	{
		this();
		System.out.println(x);
	}
	
	
	public ThisExample(int x , int y)
	{
	this(x);
	System.out.println(x+"===="+y);	}
	public static void main(String[] args) {
		
		new ThisExample(20,10);

	}

}
