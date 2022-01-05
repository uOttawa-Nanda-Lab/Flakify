@Test public void should_fail_if_property_to_filter_on_is_null(){
  try {
    filterIterable(players,null,6000L);
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The property name to filter on should not be null");
  }
}
