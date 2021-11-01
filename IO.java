import java.util.*;
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

    public Integer validInteger(String prompt, Integer start, Integer end){
        System.out.println(prompt);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b<start){System.out.println("Please input an integer larger than "+start+".");}
                else if(b>end){System.out.println("Please input an integer smaller than "+end+".");}
                else{
                    scan.nextLine();
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid input! Please input an Integer.");
                b = scan.nextInt();
            }
        }
        return b;
    }

    public String validString(String prompt){
        System.out.println(prompt);
        String s = "";
        while(true){
            try{
                s = scan.nextLine();
                break;
            }
            catch(Exception e){
                System.out.println("Invalid input! Please input a string.");
            }
        }
        return s;
    } 

    public String validSingleString(String prompt, HashSet<String> allowed){
        System.out.println(prompt);
        String s = "";
        while(true){
            try{
                s = scan.nextLine();
                if (s.length()!=1 || allowed.contains(s)==false){
                    System.out.println("Invalid input! Please input valid input.");
                }
                break;
            }
            catch(Exception e){
                System.out.println("Invalid input! Please input a character.");
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