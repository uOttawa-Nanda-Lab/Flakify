@Test public void testNull(){
  try {
    lc.getLogger((String)null);
    fail("null should cause an exception");
  }
 catch (  IllegalArgumentException e) {
  }
}
