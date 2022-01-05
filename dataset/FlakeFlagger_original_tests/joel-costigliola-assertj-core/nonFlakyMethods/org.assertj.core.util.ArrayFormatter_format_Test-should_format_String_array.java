@Test public void should_format_String_array(){
  assertEquals("[\"Hello\", \"World\"]",formatter.format(new StandardRepresentation(),new Object[]{"Hello","World"}));
}
