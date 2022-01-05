@Test public void should_fail_if_property_to_filter_on_is_null(){
  try {
    filter(players).with(null).notIn("foo","bar");
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertEquals("The property name to filter on should not be null",e.getMessage());
  }
}
