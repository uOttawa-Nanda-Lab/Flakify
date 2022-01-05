@Test public void activeSessions(){
  when(this.manager.getActiveSessions()).thenReturn(Collections.singleton("expected"));
  int result=this.adapter.getActiveSessions().size();
  assertEquals(1,result);
}
