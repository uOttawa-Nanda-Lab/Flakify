@Test public void should_not_append_String_if_already_present(){
  assertEquals("abc",Strings.append("c").to("abc"));
}
