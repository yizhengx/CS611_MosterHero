import java.util.*;

// Class IO: provides a robust io for the whole game
public class IO {
    private static IO Instance = null;
    private Scanner scan;
    
    private IO(){
        scan = new Scanner(System.in);
    }

    public static IO getInstance(){
        if (Instance!=null){return Instance;}
        else{
            Instance = new IO();
            return Instance;
        }
    }

    // return a valid user input integer from [start, end]
    public Integer validInteger(String prompt, Integer start, Integer end){
        System.out.print(prompt);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b<start){System.out.print("Please input an integer larger than "+(start-1)+":");}
                else if(b>end){System.out.print("Please input an integer smaller than "+(end+1)+":");}
                else{
                    scan.nextLine();
                    break;
                }
            }catch(Exception e){
                System.out.print("Invalid input! Please input an Integer:");
                scan.nextLine();
            }
        }
        return b;
    }
    
    public String validString(String prompt){
        System.out.print(prompt);
        String s = "";
        while(true){
            try{
                s = scan.nextLine();
                break;
            }
            catch(Exception e){
                System.out.print("Invalid input! Please input a string.");
            }
        }
        return s;
    }

    // return a valid user choice within the allowed choices
    public String validString(String prompt, HashSet<String> allowed){
        System.out.print(prompt);
        String s = "";
        while(true){
            try{
                s = scan.nextLine();
                if (allowed.contains(s)==false){
                    System.out.print("Invalid input! Please input valid input.");
                }
                else{break;}
            }
            catch(Exception e){
                System.out.print("Invalid input! Please input a character.");
            }
        }
        return s;
    } 

    public static void main(String[] args) {
        IO io = IO.getInstance();
        Integer num = io.validInteger("please input a num", 3, 10);
        System.out.println(num);
    }
}