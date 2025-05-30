import java.util.*;

class User{
    private String name ;
    private String password ; 
    private String user_name ; 
    private char gender ;
    private Long contactNo ; 
    private String email ; 
    private Boolean accountStatus ; //public = True private = False
    public static  List<Posts> userPosts ; 
    public User(String username , String password , String email){
        this.user_name= username ; 
        this.password = password ; 
        this.email = email ; 
    }

    public User(String name , String username , String password , String email , Boolean accountStatus , char gender , Long contactNo){
        this.user_name = username ; 
        this.password = password ; 
        this.email = email;
        this.accountStatus = accountStatus ; 
        this.gender = gender ; 
        this.name = name ; 
        this.contactNo = contactNo ; 
        this.userPosts = new ArrayList() ; 
    }

    public String getName(){return name ; }
    public String getPassword(){return password ; }
    public char getGender(){return gender ; }
    public static String getUserName(){return user_name ; }
    public String getEmail(){return email ; }
    public Long getContactNo(){return contactNo ; }
    public void setContactNo(Long contact){contactNo = contact ; }
    public Boolean getAccountStatus(){return accountStatus ; }
    public void setAccountStatus(Boolean status){ accountStatus = status ; }
}
class UserManager{
    List<User> myusers =  new ArrayList<>();

    public void Register(String name , String username , String password , String email , Boolean accountStatus , char gender , Long contactNo ){
        if(checkInDB(username , email , contactNo)) System.out.println("Unable to Regsiter");
        else {
            User me =  new User(name , username , password , email ,accountStatus , gender ,contactNo) ; 
            myusers.add(me) ; 
            System.out.println(" Account verified ");
        }
    }

    public boolean checkInDB(String Username , String email , Long contactNo){
        for(User user : myusers){
            if(user.getEmail() == email || user.getUserName() == Username || user.getContactNo() == contactNo){
                System.out.println("User already Exists");
                return false ; 
            }
        }
        return true ; 
    }
}
class Posts implements Ops{
    private String PostID ; 
    private String user_name ; 
    private String Context ; 
    private List<Comments> comments ;
    private static int  count = 1 ; 
    
    public String getUserName(){return user_name ; }
    public String getContext(){return Context ; }
    public void setContext(String content){this.Context = content ; }
    public List<Comments> getComments(){return comments ; }
    public String getPostID(){return PostID ; }

    public Posts(String username , String Context ,  List<Comments> comments){
        this.PostID = username + count++ + "new  " ;
        this.user_name = username ; 
        this.Context = Context ; 
        this.comments = new ArrayList<>() ; 
    }

    public void addPosts(String content){
        Posts newPost =  new Posts(getUserName() , content , getComments()  ) ; 
        User.userPosts.add(newPost) ; 
    }

    public void editPosts(String id , String content){
        for(Posts post : User.userPosts){
            if(post.PostID.equals(id)){
                post.setContext(content);
                System.out.println("Post Updated");
            }
        }
        System.out.println("Post not found");
    }

    public void AddComment(String comment){
        Comments c = new Comments(getUserName() ,comment ) ; 
        comments.add(c) ; 
    }

    public void viewAllCommments(){
        for(Comments com : comments){
            
        }
    }
}

class Comments implements Ops{
    private String username ; 
    private String comment ; 

    public Comments(String username , String comment){
        this.username = username ; 
        this.comment = comment ;
    }
}

class Message implements Ops{

}
interface Ops {

    
}



