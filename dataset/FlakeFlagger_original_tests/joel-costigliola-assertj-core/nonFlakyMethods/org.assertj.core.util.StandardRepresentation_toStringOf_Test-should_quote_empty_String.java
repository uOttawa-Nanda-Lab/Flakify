@Test public void should_quote_empty_String(){
  assertEquals("\"\"",new StandardRepresentation().toStringOf(""));
}
