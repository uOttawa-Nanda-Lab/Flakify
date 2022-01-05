@Test public void should_fail_since_actual_is_not_zero(){
  try {
    bytes.assertIsZero(someInfo(),(byte)2);
  }
 catch (  AssertionError e) {
    assertEquals("expected:<[0]> but was:<[2]>",e.getMessage());
  }
}
