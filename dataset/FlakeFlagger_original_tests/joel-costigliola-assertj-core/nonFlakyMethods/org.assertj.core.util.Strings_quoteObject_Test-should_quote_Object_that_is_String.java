@Test public void should_quote_Object_that_is_String(){
  Object o="Hello";
  assertEquals("'Hello'",Strings.quote(o));
}
