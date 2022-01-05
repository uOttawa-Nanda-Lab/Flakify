@Test public void getAllSessions(){
  String expected="expected";
  when(this.manager.getLocalSessions()).thenReturn(Collections.singleton(expected));
  Set<String> result=this.adapter.getAllSessions();
  assertEquals(1,result.size());
  assertSame(expected,result.iterator().next());
}
