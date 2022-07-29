package io.undertow.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Matej Lazar
 */
public class HttpStringTestCase {

    @Test public void testOrderShorterFirst(){HttpString a=new HttpString("a");HttpString aa=new HttpString("aa");Assert.assertEquals(-1,a.compareTo(aa));}

}
