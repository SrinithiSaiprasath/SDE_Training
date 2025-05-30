//upcasting downcasting example
class A{
	public void show1() {
		System.out.println("A In show");
	}
}

class B extends A{
	public void show2() {
		System.out.println("B In Show");
	}
}

public class Solution1{
	public static void main(String[] args) {
		// A obj = (A) new B(); //UPCASTING  from child to parent

		A obj =  new B() ;  //reference of A and object of B
		obj.show1();
		// obj.show2(); //ERROR

		// B obj = (B) new A(); //DOWNCASTING
		B obj1 = (B) obj ; //from parent to child
		obj1.show2();

		
	}
}
