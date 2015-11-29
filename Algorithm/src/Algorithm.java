import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Algorithm {
	
	static String line = null, line2 = null, temp = null;
	static JTextArea display;
	static StringTokenizer st;
	
	static final String[] accessModifiers = {"public", "private", "protected"};
	static final String[] nonAccessModifiers = {"static", "final", "abstract"};
	static final String[] dataTypes = {"void", "byte", "short", "int", "long", "float", "double", "char", "String", "boolean"};
	
	//plagiarism counters
	//level1
	static boolean level1Plag = true;
	//level2
	static int level2Counter = 0;
	static int openBracketCount = 1, closeBracketCount = 0;
	static LineNumberReader file1Marker, file2Marker;

	public static void main(String[] args) {
		BufferedReader br = null, br2 = null, temp = null, temp2 = null;
		
		String file = System.getProperty("user.dir");
		
		 JPanel middlePanel = new JPanel ();
		 middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );

		    // create the middle panel components

		    display = new JTextArea ( 16, 58 );
		    display.setEditable ( false ); // set textArea non-editable
		    display.setText("");
		    JScrollPane scroll = new JScrollPane ( display );
		    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

		    //Add Textarea in to middle panel
		    middlePanel.add ( scroll );

		    // My code
		    JFrame frame = new JFrame ();
		    frame.add ( middlePanel );
		    frame.pack ();
		    frame.setLocationRelativeTo ( null );
		    frame.setVisible ( true );
//		
//		String line = null, line2 = null;
//		
//		try {
//			
//			while ((line = br.readLine()) != null && (line2 = br2.readLine()) != null) {
//				
//				System.out.println(i++);
//				
//				if (line.trim().isEmpty())
//					System.out.println("line 1 has whitespace");
//				if (line2.trim().isEmpty())
//					System.out.println("line 2 has whitespace");
//			   
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
			//levelOne(br, br2);
			
			try {
				br = temp = new BufferedReader(new FileReader(file  + "\\test.txt"));
				br2 = new BufferedReader(new FileReader(file  + "\\test2.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);;
			}
			
			
			levelTwo(br, br2);
			
			
			System.out.println(level1Plag);
		
	}
	
	//level one process compares the whole file, if it is identical then there's no editing done
	public static void levelOne(BufferedReader file1, BufferedReader file2)
	{
		int i = 1, count = 0;
		
		try 
		{	
			while ((line = file1.readLine()) != null | (line2 = file2.readLine()) != null) 
			{
				
				display.append("line " + i++ + "\n");
				
				//CHECK FOR LINES OF WHITESPACES
				if (line.trim().isEmpty())
				{
					display.append("code 1: whitespace\n");
					while(line.trim().isEmpty())
						line = temp = file1.readLine();
					display.append("code 1: " + line + "\n");
				}
				else
					display.append("code 1: " + line + "\n");
				
				if (line2.trim().isEmpty()){
					display.append("code 2: whitespace\n");
					while(line2.trim().isEmpty())
						line2 = file2.readLine();
					display.append("code 2: " + line2 + "\n");
				}
				else
					display.append("code 2: " + line2 + "\n");
				
				//COMPARISON SECTION
				
				//if one file is done but the other is not, level 1 plagiarism passed
				if((line == null) && (line2 != null) || 
	    				(line != null) && (line2 == null))
	    		{
	    			level1Plag = false;
	    			System.out.println("1");
	    			break;
	    		}
				//current line is identical; level 1 plagiarism continues checking
	    		else if(line.equals(line2))
	    		{
	    			level1Plag = true;
	    			System.out.println("Identical line count: " + ++count);
	    			continue;
	    		}
				//current line is not identical; level 1 plagiarism passed
	    		else
	    		{
	    			level1Plag = false;
	    			System.out.println("3");
	    			break;
	    		}
		    	
			   
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//find class name
	public static void levelTwo(BufferedReader file1, BufferedReader file2)
	{
		
		try
		{
			while((line = file1.readLine()) != null)
			{
				if (line.trim().isEmpty())
				{
					display.append("code 1: whitespace\n");
					line = file1.readLine();
					display.append("code 1: " + line + "\n");
				}
				else
					display.append("code 1: " + line + "\n");
				
//				if (line2.trim().isEmpty()){
//					display.append("code 2: whitespace\n");
//					line2 = file2.readLine();
//				}
//				else
//					display.append("code 2: " + line2 + "\n");
				
				//tokenization
				
				st = new StringTokenizer(line);
				String token = "";
				int start, end;
				
				
			     while (st.hasMoreTokens()) {
			    	 token = st.nextToken(" |\\{");
			         if(Arrays.asList(accessModifiers).contains(token))
			         {
			        	 token = st.nextToken(" |\\{");
			        	 if(Arrays.asList(nonAccessModifiers).contains(token))
			        	 {
			        		 token = st.nextToken(" |\\{");
				        	 if(Arrays.asList(dataTypes).contains(token))
				        	 {
				        		 start = file1Marker.getLineNumber();
				        		 
				        		 System.out.println(start);
				        		 
				        		 while(openBracketCount != closeBracketCount)
				        			 findBrackets(line);
				        		 
				        		 end = file1Marker.getLineNumber();
				        		 
				        		 System.out.println(end);
				        	 }
			        	 }
			        	 else
			        		 System.out.println("\n\n\ngot it!! " + st.nextToken());
			         }
			         else
			        	 continue;
			     }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error reading. " + e);
		}
	}
	
	public static void findBrackets(String str)
	{
		int charCount = 0;
		while(str.charAt(charCount++) != '\u0000')
		{
			if(openBracketCount == 1)
				break;
			else if(str.charAt(charCount) == '{')
				openBracketCount++;
			else if(str.charAt(charCount) == '}')
				closeBracketCount++;
		}
//		String[] lines = str.split("\r\n|\r|\n");
//		int num = lines.length;
//		String[] file = new String[num];
//		char b1 = '{'; int line1;
//		char b2 = '}'; int line2;
//		for(int i=0; i<num; i++){
//			if(lines[i].indexOf(b1) == -1)
//				continue;
//			else{
//				line1 = i;
//				break;
//			}
//				
//		}
		//pug end of the bracket, reverse lang yung for loop
	}

}
