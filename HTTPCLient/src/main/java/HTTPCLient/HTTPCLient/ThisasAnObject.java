package HTTPCLient.HTTPCLient;

public class ThisasAnObject {

	
	public void objMethod(ThisasAnObject obj)
	{
		System.out.println("Method is called by passing Object of the class");
	}
	
	public void initializeMehtod() {
		
		objMethod(new ThisasAnObject());
		System.out.println("value of this is ===="+this.toString());
		ThisasAnObject good=new ThisasAnObject();
		System.out.println("good example==="+good);
	
		
	}
	public static void main(String[] args) {

		
		ThisasAnObject th = new ThisasAnObject();
		//th.objMethod(th);
		th.initializeMehtod();
		System.out.println(th.toString());
		
		
		
		
	}

}
