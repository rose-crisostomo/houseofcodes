
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd {

    Runtime rt = Runtime.getRuntime();
    public static void main(String[] args) throws IOException, InterruptedException {
        
        String homedir = System.getProperty("user.home");
        //create folder
        //process("mkdir compiler");
        //compile
        process(homedir + "\\compiler\\", "c:\\Windows\\Microsoft.NET\\Framework\\v3.5\\CSC.EXE sample.cs");
        //execute
        process(null,homedir + "\\compiler\\sample");
    }
    
    public static void process(String command, String exe) throws IOException, InterruptedException{
        final Process p;
        if(command != null)
            p = Runtime.getRuntime().exec(exe, null, new File(command));
        else
            p = Runtime.getRuntime().exec(exe);

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
    }
}
