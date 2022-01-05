@Test public void should_fail_since_actual_is_zero(){
  try {
    doubles.assertIsNotZero(someInfo(),0.0);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0.0>\nnot to be equal to:\n <0.0>\n");
  }
}
