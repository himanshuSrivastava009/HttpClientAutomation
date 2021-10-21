package HTTPCLient.HTTPCLient;




public class CallingusingClass {
	boolean b;
	public void testSum()
	{
		System.out.println("this is sum");
	}

	public static void main(String[] args) {
		

	
		CallingusingClass cl = new CallingusingClass();
		System.out.println(cl.getClass());
		Class c = boolean.class;  // correct
		System.out.println("===="+boolean.class);
	
	}
	
	

}


