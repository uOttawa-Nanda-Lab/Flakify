@Test public void getDefaultCacheConfiguration(){
  Configuration config=new ConfigurationBuilder().build();
  when(this.manager.getDefaultCacheConfiguration()).thenReturn(config);
  Configuration result=this.subject.getDefaultCacheConfiguration();
  assertSame(config,result);
}
