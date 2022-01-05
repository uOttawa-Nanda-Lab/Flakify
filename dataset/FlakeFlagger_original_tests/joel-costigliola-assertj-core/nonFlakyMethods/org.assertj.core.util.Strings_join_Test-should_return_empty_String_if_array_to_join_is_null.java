@Test public void should_return_empty_String_if_array_to_join_is_null(){
  assertEquals("",Strings.join((String[])null).with("|"));
}
