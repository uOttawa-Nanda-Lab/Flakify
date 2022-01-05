@Test public void should_fail_since_actual_is_zero(){
  try {
    floats.assertIsNotZero(someInfo(),0.0f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0.0f>\nnot to be equal to:\n <0.0f>\n");
  }
}
