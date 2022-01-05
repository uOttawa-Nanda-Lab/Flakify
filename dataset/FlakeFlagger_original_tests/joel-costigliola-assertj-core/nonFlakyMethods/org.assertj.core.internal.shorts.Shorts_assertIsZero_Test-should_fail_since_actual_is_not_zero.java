@Test public void should_fail_since_actual_is_not_zero(){
  try {
    shorts.assertIsZero(someInfo(),(short)2);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0]> but was:<[2]>");
  }
}
