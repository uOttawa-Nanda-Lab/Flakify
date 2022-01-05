@Test public void should_fail_since_actual_is_not_equal_to_NaN(){
  try {
    floats.assertIsNaN(someInfo(),6.0f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[NaN]f> but was:<[6.0]f>");
  }
}
