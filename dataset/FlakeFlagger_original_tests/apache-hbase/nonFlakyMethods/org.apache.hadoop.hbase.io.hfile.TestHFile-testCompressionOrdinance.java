/** 
 * Make sure the orginals for our compression libs doesn't change on us.
 */
public void testCompressionOrdinance(){
  assertTrue(Compression.Algorithm.GZ.ordinal() == 1);
  assertTrue(Compression.Algorithm.NONE.ordinal() == 2);
}
