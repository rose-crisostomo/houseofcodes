
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.Scanner;

public class cmd {

    static String homedir = System.getProperty("user.home");
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner a = new Scanner(System.in);
        String ans = "Y";
        String filename = "sample"; //ibahin nyo na lang to

        do {
            //main menu
            System.out.println("[1] Compile C#\n[2] Run C#\n\n");
            System.out.println("[3] Compile C\n[4] Run C\n\n");
            System.out.println("[5] Compile C++\n[6] Run C++\n\n");
            System.out.println("[7] Compile Java\n[8] Run Java\n\n");

            int choice = a.nextInt();

            switch (choice) {
                case 1:
                    CSharpCompiler(filename + ".cs");
                    break;
                case 2:
                    CSharpRun(filename + ".exe");
                    break;
                case 3: break;
                case 4:break;
                case 5: 
                    compileCpp();
					break;
                case 6: 
					runCpp();
					break;
                case 7: break;
                case 8: break;
            }

            System.out.println("Enter again [y/n]: ");
            ans = a.next();
            ans = ans.toUpperCase();
            
            if(ans != "Y"){
                break;
            }

        } while (true);
    }

    public static void CSharpCompiler(String cs) throws IOException, InterruptedException {
        //create folder
        //process("mkdir compiler");
        //compile
        CSharpProcess(homedir + "\\compiler\\", "c:\\Windows\\Microsoft.NET\\Framework\\v3.5\\CSC.EXE " + cs);
    }

    public static void CSharpRun(String filename) throws IOException, InterruptedException {
        String[] argss = {"cmd", "/c", "start", homedir + "\\compiler\\" + filename};
        ProcessBuilder pb = new ProcessBuilder(argss);
        pb.start();
    }

    public static void CSharpProcess(String command, String exe) throws IOException, InterruptedException {
        final Process p;
        if (command != null) {
            p = Runtime.getRuntime().exec(exe, null, new File(command));
        } else {
            p = Runtime.getRuntime().exec(exe);
        }

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
	
     public static void compileCpp(){
        String compileFileCommand = "g++ " + "C:\\cygnus\\cygwin-b20\\include\\g++.exe Sample.cpp" ;
        String resultString = "";
        try
        {
            System.out.println("Compiling CPP File");

            Process pr = Runtime.getRuntime().exec(compileFileCommand);
            pr.waitFor();
            
            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String errCompile = "";
            while((errCompile = brCompileError.readLine()) != null) {
                    System.out.println(errCompile);
            }
            resultString += errCompile + "\n";
            
            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String outputCompile = "";
            while((outputCompile = brCompileRun.readLine()) != null) {
                    System.out.println(outputCompile);
            }
            resultString += outputCompile + "\n";
            pr.waitFor();
            
        }catch(Exception e){
            System.out.println("Excpetion ");
            System.out.println(e.getMessage());
        }
    }
	
	
        public static void runCpp(){
        String runFileCommand = "./a.out";
        try
        {
            System.out.println("Running CPP File");
                        
            Process pr = Runtime.getRuntime().exec(runFileCommand);
            
            BufferedReader brRun = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String errRun = "";
            while((errRun = brRun.readLine()) != null) {
                    System.out.println(errRun);
            }
            
            BufferedReader brResult = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String outputRun = "";
            while((outputRun = brResult.readLine()) != null) {
                    System.out.println(outputRun);
            }
            brResult.close();
            pr.waitFor();
            
        }catch(Exception e){
            System.out.println("Excpetion ");
            System.out.println(e.getMessage());
        }
    }
	
}
