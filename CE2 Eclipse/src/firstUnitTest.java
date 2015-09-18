
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
		
	@Ignore	
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
	
	@Ignore	
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
	
	
	@Ignore
	public void testSortTexts() {
		tb.clearAll();
		tb.addString("a line like this");
		tb.addString("A line like this");
		tb.addString("Z line like this");
		tb.addString("z line like this");
		tb.addString("1 this starts with a number");
		tb.addString("2 ah this also starts with a number");
		
		tb.sortTexts();
		
		ArrayList<String> testList = new ArrayList<String>();
		
		testList.add("1 this starts with a number");
		testList.add("2 ah this also starts with a number");
		testList.add("A line like this");
		testList.add("Z line like this");
		testList.add("a line like this");
		testList.add("z line like this");
		
		assertEquals(tb.textsList, testList);
	}
	
	@Test
	public void testSortTexts2() {
		tb.clearAll();
		tb.addString("-- weird line");
		tb.addString("1 number");
		tb.addString("1 numaer");
		tb.addString("CAPS");
		tb.addString("caps not");
		
		tb.sortTexts();
		
		ArrayList<String> testList = new ArrayList<String>();
		
		testList.add("-- weird line");
		testList.add("1 numaer");
		testList.add("1 number");
		testList.add("CAPS");
		testList.add("caps not");
		
		tb.displayAll();
		
		assertEquals(tb.textsList, testList);
	}
}
