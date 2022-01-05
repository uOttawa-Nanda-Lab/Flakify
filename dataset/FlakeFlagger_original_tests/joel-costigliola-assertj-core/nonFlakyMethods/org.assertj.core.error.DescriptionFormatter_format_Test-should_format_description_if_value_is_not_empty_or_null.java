@Test public void should_format_description_if_value_is_not_empty_or_null(){
  assertEquals("[Leia] ",formatter.format(new TestDescription("Leia")));
}
