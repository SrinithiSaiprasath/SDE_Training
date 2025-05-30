// import java.util.* ;

// public class User{
//     private String name;
//     int usercount = 1 ; 
//     private String uid ; 
//     private String contactno ;
//     @SuppressWarnings("unused")
//     private String emailid ; 
//     private String address ;
//     private String password ;  
//     List<String> users = new ArrayList<>() ; 
//     public User(String name , String contactno ,  String emailid , String address, String password){
//         this.name = name;
//         this.contactno = contactno ; 
//         this.emailid = emailid ;
//         this.address = address;
//         this.uid = name + "_"+ usercount++ ; 
//         this.password =password;
//         // usercount++;
//         users.add(uid) ; 
//     }

//     public String getName(){return name;}
//     public String getPassword(){return password ; }
//     public String setPassWord(String newpassword){return this.password = newpassword ; }
//     public String getContact(){return contactno;}
//     public String getAddress(){return address;}
//     public String getId(){return uid ; }
// }