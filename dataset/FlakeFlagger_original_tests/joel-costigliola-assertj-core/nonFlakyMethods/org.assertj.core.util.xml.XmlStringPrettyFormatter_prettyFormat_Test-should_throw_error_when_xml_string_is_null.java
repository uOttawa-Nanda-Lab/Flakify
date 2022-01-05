@Test public void should_throw_error_when_xml_string_is_null(){
  try {
    xmlPrettyFormat(null);
    shouldHaveThrown(IllegalArgumentException.class);
  }
 catch (  Exception e) {
    assertThat(e).isInstanceOf(IllegalArgumentException.class).hasMessageStartingWith("Expecting XML String not to be null");
  }
}
