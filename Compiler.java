
package thesis;

import java.io.*;
 
public class Compiler {
    
    public static void compileFile(){
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
    
        public static void runFile(){
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
    
    public static void main(String args[]) {
        compileFile();
        runFile();
        
        }
}