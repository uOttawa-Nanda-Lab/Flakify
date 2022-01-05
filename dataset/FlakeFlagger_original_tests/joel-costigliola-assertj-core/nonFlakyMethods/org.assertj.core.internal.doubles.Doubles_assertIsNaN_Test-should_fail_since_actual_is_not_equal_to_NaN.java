@Test public void should_fail_since_actual_is_not_equal_to_NaN(){
  try {
    doubles.assertIsNaN(someInfo(),6d);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[NaN]> but was:<[6.0]>");
  }
}
