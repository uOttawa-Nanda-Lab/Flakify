@Test public void should_join_using_iterable_delimeter_and_escape(){
  assertEquals("'Luke'|'Leia'|'Han'",Strings.join(newArrayList("Luke","Leia","Han")).with("|","'"));
}
