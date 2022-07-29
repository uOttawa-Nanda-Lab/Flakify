package ninja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CookieTest {

    @Test public void testThatBuilderRejectsNullValues(){boolean gotException=false;try {Cookie.builder("",null);} catch (NullPointerException nullPointerException){gotException=true;}assertTrue(gotException);}

}
