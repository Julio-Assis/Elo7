import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class TestSounder1 extends TestCase  {
   private Upland upland;
   
   
   @Before 
   public void setUp() {
		Scanner sc;
		try {
			sc = new Scanner(new File("inputElo7.txt"));
			int width = sc.nextInt() + 1;
			int height = sc.nextInt() + 1;
			upland = new Upland(width, height);
			while(sc.hasNext()){
				int posX = sc.nextInt();
				int posY = sc.nextInt();
				String dir = sc.next();
				String comands = sc.next();
				upland.addOperator(new Operator(posX,posY,dir,comands,upland));				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	
   @Test
   public void testAdd() {
		upland.processOperatorComands();
		Vector<Operator> operators = upland.getOperators();
		Vector<String> answers = new Vector<>();
		answers.addElement("1 3 N");
		answers.addElement("5 1 E");
		for(int i = 0; i < operators.size(); i++){
			assertEquals(answers.elementAt(i), operators.elementAt(i).getState());
		}
   }
	
   //tearDown used to close the connection or clean up activities
   public void tearDown(  ) {
   }
}