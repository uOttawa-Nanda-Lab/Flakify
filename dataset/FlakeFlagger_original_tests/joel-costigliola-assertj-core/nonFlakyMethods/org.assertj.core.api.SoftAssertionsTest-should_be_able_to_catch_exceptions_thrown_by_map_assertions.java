@SuppressWarnings("unchecked") @Test public void should_be_able_to_catch_exceptions_thrown_by_map_assertions(){
  try {
    softly.assertThat(Maps.mapOf(MapEntry.entry("54","55"))).contains(MapEntry.entry("1","2"));
    softly.assertAll();
    fail("Should not reach here");
  }
 catch (  SoftAssertionError e) {
    List<String> errors=e.getErrors();
    assertThat(errors).contains("\nExpecting:\n" + " <{\"54\"=\"55\"}>\n" + "to contain:\n"+ " <[MapEntry[key='1', value='2']]>\n"+ "but could not find:\n"+ " <[MapEntry[key='1', value='2']]>\n");
  }
}
