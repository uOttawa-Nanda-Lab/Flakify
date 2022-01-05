@Test public void getDefaultCacheName(){
  String defaultCache="default";
  GlobalConfiguration global=new GlobalConfigurationBuilder().defaultCacheName(defaultCache).build();
  when(this.manager.getCacheManagerConfiguration()).thenReturn(global);
  assertSame(defaultCache,this.subject.getDefaultCacheName());
}
