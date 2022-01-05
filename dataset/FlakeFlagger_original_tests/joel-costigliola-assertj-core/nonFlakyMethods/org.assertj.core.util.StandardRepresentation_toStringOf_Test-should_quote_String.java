@Test public void should_quote_String(){
  assertEquals("\"Hello\"",new StandardRepresentation().toStringOf("Hello"));
}
