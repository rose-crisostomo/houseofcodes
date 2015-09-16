package cmplr;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cmplr {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        String location = System.getProperty("user.dir") + "\\C\\exec.bat"; 
        
        Runtime rt = Runtime.getRuntime();
        final Process p = Runtime.getRuntime().exec(location);
        
        new Thread(new Runnable() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        p.waitFor();
     
        String[] argss = {"cmd", "/c", "start", System.getProperty("user.dir") + "\\C\\yourprogram"};
        ProcessBuilder pb = new ProcessBuilder(argss);
        pb.start();
        
    }
}
