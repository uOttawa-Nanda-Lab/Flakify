@Test public void should_fail_since_actual_is_not_zero(){
  try {
    longs.assertIsZero(someInfo(),2l);
  }
 catch (  AssertionError e) {
    assertEquals("expected:<[0]L> but was:<[2]L>",e.getMessage());
  }
}
