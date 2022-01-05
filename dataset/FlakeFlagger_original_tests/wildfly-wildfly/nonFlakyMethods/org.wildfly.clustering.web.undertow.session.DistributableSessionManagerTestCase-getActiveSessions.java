@Test public void getActiveSessions(){
  String expected="expected";
  when(this.manager.getActiveSessions()).thenReturn(Collections.singleton(expected));
  Set<String> result=this.adapter.getActiveSessions();
  assertEquals(1,result.size());
  assertSame(expected,result.iterator().next());
}
