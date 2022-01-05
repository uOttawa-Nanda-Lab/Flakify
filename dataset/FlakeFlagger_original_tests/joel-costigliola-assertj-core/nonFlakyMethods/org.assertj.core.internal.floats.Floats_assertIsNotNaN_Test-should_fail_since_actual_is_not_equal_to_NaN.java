@Test public void should_fail_since_actual_is_not_equal_to_NaN(){
  try {
    floats.assertIsNotNaN(someInfo(),6f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"<6.0> should not be equal to:<NaN>");
  }
}
