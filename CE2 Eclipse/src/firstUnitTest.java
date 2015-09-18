
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
//import org.junit.Test;

public class firstUnitTest {


	TextBuddy tb = new TextBuddy("saveFile.txt");
	
	@Test	
	public void testSearchTexts() {
		tb.addString("first case");
		tb.addString("2nd case");
		tb.addString("Third case");
		tb.addString("2nd case again");
		
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("2nd case");
		testList.add("2nd case again");
		
		String searchWord = "2nd";
		ArrayList<String> resultList = tb.searchTexts(searchWord);
		
		assertEquals(resultList, testList);
	}
		
	
}
