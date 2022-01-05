@Test public void getTransientSessions(){
  Set<String> result=this.adapter.getTransientSessions();
  assertTrue(result.isEmpty());
}
