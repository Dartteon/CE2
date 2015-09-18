
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
//import org.junit.Test;

public class firstUnitTest {


	TextBuddy tb = new TextBuddy("saveFile.txt");
	
	@Ignore	
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
		
	@Test	
	public void testSearchTexts2() {
		tb.addString("keyword one two three");
		tb.addString("key word not gonna appear");
		tb.addString("keyword delete me pls");
		tb.addString("key keyword word");
		tb.deleteLine("3");
		
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("keyword one two three");
		testList.add("key keyword word");
		
		String searchWord = "keyword";
		ArrayList<String> resultList = tb.searchTexts(searchWord);
		
		assertEquals(resultList, testList);
	}
	
	@Test	
	public void testSearchTexts3() {
		tb.addString("1");
		tb.addString("2");
		tb.addString("3");
		tb.addString("4");
		
		ArrayList<String> testList = new ArrayList<String>();
		
		String searchWord = "5";
		ArrayList<String> resultList = tb.searchTexts(searchWord);
		
		assertEquals(resultList, testList);
	}
}
