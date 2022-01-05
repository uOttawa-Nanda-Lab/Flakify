@Test public void getName(){
  String name="foo";
  GlobalConfiguration global=new GlobalConfigurationBuilder().globalJmxStatistics().cacheManagerName(name).build();
  when(this.manager.getCacheManagerConfiguration()).thenReturn(global);
  assertSame(name,this.subject.getName());
}
