@Test public void testValidRange(){
  String[] validRanges={"1-8","-7--1","7--1","1st Fri-1st Mon"};
  for (  String validRange : validRanges) {
    boolean accepts=RangeValue.accepts(validRange);
    Assert.assertTrue("Valid range value wasn't accepted: " + validRange,accepts);
    RangeValue validRangeValue=new RangeValue(validRange);
  }
}
