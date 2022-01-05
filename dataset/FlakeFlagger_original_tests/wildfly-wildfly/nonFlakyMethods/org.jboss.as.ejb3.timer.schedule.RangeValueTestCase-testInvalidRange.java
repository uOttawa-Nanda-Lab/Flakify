@Test public void testInvalidRange(){
  String[] invalidRangeValues={null,""," ","0.1","1d","1.0","?","%","$","!","&","-","/",",",".","1-","1-2-3","1+2","**","*-","*,1","1,*","5/*","1, 2/2","---","-","--"," -2 -3 -4","-0","1--"};
  for (  String invalidRange : invalidRangeValues) {
    boolean accepts=RangeValue.accepts(invalidRange);
    Assert.assertFalse("Range value accepted an invalid value: " + invalidRange,accepts);
    try {
      RangeValue invalidRangeValue=new RangeValue(invalidRange);
      Assert.fail("Range value did *not* throw IllegalArgumentException for an invalid range: " + invalidRange);
    }
 catch (    IllegalArgumentException iae) {
    }
  }
}
