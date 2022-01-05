@Test public void getApplications(){
  Set<String> expected=Collections.singleton("deployment");
  when(this.map.keySet()).thenReturn(expected);
  Set<String> result=this.sessions.getDeployments();
  assertEquals(expected,result);
  verify(this.mutator,never()).mutate();
}
