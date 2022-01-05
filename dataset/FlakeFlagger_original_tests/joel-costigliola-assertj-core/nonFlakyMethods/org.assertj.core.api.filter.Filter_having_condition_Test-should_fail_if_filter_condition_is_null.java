@Test public void should_fail_if_filter_condition_is_null(){
  try {
    filter(players).having(null);
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The filter condition should not be null");
  }
}
