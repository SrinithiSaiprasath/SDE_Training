
// abstract class A {
//     void m() ; 
// }
// OP : This method requires a body instead of a semicolonJava(603979883)

// public class A{
//     abstract void m(){
//         System.out.println("Hello");
//     }
// }
//The abstract method m in type A can only be defined by an abstract classJava(67109227)

// public abstract class A {
//     abstract void m();
//     A(){} ; 
//     void m() {} ; 
// }
// OP : Duplicate method m() in type AJava(67109219)

// public abstract class A {
//     abstract void m();
//     abstract int a = 400;
// }
// Illegal modifier for the field a; only public, protected,
//  private, static, final, transient & volatile are permittedJava(33554774)
