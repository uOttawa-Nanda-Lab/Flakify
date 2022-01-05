@Test public void getCacheManagerConfiguration(){
  GlobalConfiguration global=new GlobalConfigurationBuilder().build();
  when(this.manager.getCacheManagerConfiguration()).thenReturn(global);
  GlobalConfiguration result=this.subject.getCacheManagerConfiguration();
  assertSame(global,result);
}
