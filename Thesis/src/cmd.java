
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

public class cmd extends JFrame{

    static String homedir = System.getProperty("user.dir");
    private ClientPanel currentPanel;
    
    public cmd(){
    	setTitle("CLIENT");
    	
    	currentPanel = new ClientPanel();
    	
    	this.setContentPane(currentPanel);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	cmd gui = new cmd();
    	gui.setVisible(true);
    	gui.setSize(450, 400);
    	//        Scanner a = new Scanner(System.in);
//        String ans = "Y";
//
//        do {
//            //main menu
//            System.out.println("[1] Compile C#\n[2] Run C#\n\n");
//            System.out.println("[3] Compile C\n[4] Run C\n\n");
//            System.out.println("[5] Compile C++\n[6] Run C++\n\n");
//            System.out.println("[7] Compile Java\n[8] Run Java\n\n");
//            int choice = a.nextInt();
//
//            switch (choice) {
//                case 1:
//                    CSharpCompiler();
//                    break;
//                case 2:
//                    Run(homedir + "\\C#\\thesis");
//                    break;
//                case 3: 
//                    CCompiler();
//                    break;
//                case 4: 
//                    Run(homedir + "\\C\\thesis");
//                    break;
//
//                case 5:
//                    CppCompiler();
//                    break;
//                case 6: 
//                    Run(homedir + "\\C++\\thesis");
//                    break;
//                case 7: JavaCompiler();
//                    break;
//                case 8: 
//                    Run("java sample");
//                    break;
//            }
//
//            System.out.println("Enter again [y/n]: ");
//            ans = a.next();
//            ans = ans.toUpperCase();
//            
//            if(ans != "Y"){
//                break;
//            }
//
//        } while (true);
//        
//        a.close();
    }

    public static void CSharpCompiler() throws IOException, InterruptedException {
        Process(homedir + "\\C#\\", " c:\\Windows\\Microsoft.NET\\Framework\\v3.5\\csc /out:thesis.exe *.cs");
    }

    public static void CCompiler() throws IOException, InterruptedException{
        Process(homedir + "\\C\\", " g++ *.c -o thesis");
    }
    
    public static void CppCompiler() throws IOException, InterruptedException{
        Process(homedir + "\\C++\\", " g++ *.cpp -o thesis");
    }
    
    public static void JavaCompiler() throws IOException, InterruptedException{
        Process(homedir + "\\Java\\", "C:\\Program Files\\Java\\jdk" + System.getProperty("java.version") + "\\bin\\javac *.java");
    }
    
    //Runs the compiled program regardless of language
    public static void Run(String command) throws IOException, InterruptedException {
        String[] argss = {"cmd", "/c", "start", command};
        
        ProcessBuilder pb;
        
        pb = new ProcessBuilder(argss);
        pb.start();
    }
    
    //function to process command lines
    public static void Process(String command, String exe) throws IOException, InterruptedException {
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
}
