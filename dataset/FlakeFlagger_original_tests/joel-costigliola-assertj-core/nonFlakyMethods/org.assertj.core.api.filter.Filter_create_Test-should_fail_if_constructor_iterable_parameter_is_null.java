@Test public void should_fail_if_constructor_iterable_parameter_is_null(){
  try {
    filter((List<Player>)null);
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The iterable to filter should not be null");
  }
}
