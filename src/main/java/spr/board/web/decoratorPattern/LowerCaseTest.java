package spr.board.web.decoratorPattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseTest {
	public static void main(String[] args) {
		
		int c;
		try{
			InputStream in = 
					new LowerCaseInputStream(
							new FileInputStream("C:/test.txt"));
			while((c = in.read()) >= 0 ) {
				System.out.println((char)c);
			}
			
			in.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
