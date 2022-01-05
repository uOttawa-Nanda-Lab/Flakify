@Test public void should_fail_if_constructor_array_parameter_is_null(){
  try {
    filter((Player[])null);
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The array to filter should not be null");
  }
}
