import java.io.*; 
public class inputTest {
    public static void main(String[] args) throws IOException {
        BufferedInputStream br = new BufferedInputStream(System.in);
        byte [] read = new byte[1024];
        StringBuffer sb = new StringBuffer();
        int j = 0;
        for (int i; (i = br.read(read)) != 1;){
        sb.append(new String(read, 0, i));
        j++;
        if (j > 3){
        break;
        }
        }
        br.close();
        System.out.println(sb);
    }
}