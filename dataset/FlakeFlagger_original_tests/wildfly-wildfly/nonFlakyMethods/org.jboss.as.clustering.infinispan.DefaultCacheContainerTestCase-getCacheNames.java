@Test public void getCacheNames(){
  Set<String> caches=Collections.singleton("other");
  when(this.manager.getCacheNames()).thenReturn(caches);
  Set<String> result=this.subject.getCacheNames();
  assertEquals(1,result.size());
  assertTrue(result.contains("other"));
}
