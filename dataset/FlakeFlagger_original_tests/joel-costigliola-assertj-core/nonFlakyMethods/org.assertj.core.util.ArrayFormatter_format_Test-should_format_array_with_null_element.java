@Test public void should_format_array_with_null_element(){
  assertEquals("[\"Hello\", null]",formatter.format(new StandardRepresentation(),new Object[]{"Hello",null}));
}
