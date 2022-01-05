@Test public void should_fail_since_actual_is_not_zero(){
  try {
    floats.assertIsZero(someInfo(),2.0f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0].0f> but was:<[2].0f>");
  }
}
