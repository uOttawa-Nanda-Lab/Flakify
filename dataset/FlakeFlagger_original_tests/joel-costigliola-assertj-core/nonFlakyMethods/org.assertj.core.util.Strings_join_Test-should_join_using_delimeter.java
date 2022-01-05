@Test public void should_join_using_delimeter(){
  assertEquals("Luke|Leia|Han",Strings.join("Luke","Leia","Han").with("|"));
}
