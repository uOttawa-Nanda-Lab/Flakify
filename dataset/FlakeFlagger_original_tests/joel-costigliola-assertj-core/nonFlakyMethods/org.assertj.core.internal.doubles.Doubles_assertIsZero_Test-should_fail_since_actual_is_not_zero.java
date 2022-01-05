@Test public void should_fail_since_actual_is_not_zero(){
  try {
    doubles.assertIsZero(someInfo(),2.0d);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0].0> but was:<[2].0>");
  }
}
