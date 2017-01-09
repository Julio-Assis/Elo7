import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestSounder extends Operator {
	
   Operator op = new Operator();
   
   
   @Test
   public void testPrintMessage() {
	  message = "Deu ruim!";
      assertEquals(message,messageUtil.printMessage());
   }
}