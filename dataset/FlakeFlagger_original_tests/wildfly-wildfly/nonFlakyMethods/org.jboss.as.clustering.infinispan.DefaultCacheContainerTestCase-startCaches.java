@Test public void startCaches(){
  when(this.manager.startCaches("other")).thenReturn(this.manager);
  EmbeddedCacheManager result=this.subject.startCaches("other");
  assertSame(this.subject,result);
}
