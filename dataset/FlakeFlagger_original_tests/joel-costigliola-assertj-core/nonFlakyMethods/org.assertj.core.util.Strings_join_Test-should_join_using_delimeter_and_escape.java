@Test public void should_join_using_delimeter_and_escape(){
  assertEquals("'Luke'|'Leia'|'Han'",Strings.join("Luke","Leia","Han").with("|","'"));
}
