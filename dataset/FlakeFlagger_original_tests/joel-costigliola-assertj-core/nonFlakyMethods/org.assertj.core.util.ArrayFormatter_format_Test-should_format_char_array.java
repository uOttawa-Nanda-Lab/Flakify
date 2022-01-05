@Test public void should_format_char_array(){
  assertEquals("['a', 'b', 'c']",formatter.format(new StandardRepresentation(),new char[]{'a','b','c'}));
}
