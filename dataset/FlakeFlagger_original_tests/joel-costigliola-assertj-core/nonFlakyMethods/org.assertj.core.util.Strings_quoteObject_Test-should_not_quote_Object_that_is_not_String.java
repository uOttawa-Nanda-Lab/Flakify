@Test public void should_not_quote_Object_that_is_not_String(){
  assertEquals(9,Strings.quote(9));
}
