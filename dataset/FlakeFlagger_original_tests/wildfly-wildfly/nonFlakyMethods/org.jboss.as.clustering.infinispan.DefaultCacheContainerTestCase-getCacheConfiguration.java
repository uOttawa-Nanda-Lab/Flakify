@Test public void getCacheConfiguration(){
  Configuration config=new ConfigurationBuilder().build();
  when(this.manager.getCacheConfiguration("cache")).thenReturn(config);
  Configuration result=this.subject.getCacheConfiguration("cache");
  assertSame(config,result);
}
