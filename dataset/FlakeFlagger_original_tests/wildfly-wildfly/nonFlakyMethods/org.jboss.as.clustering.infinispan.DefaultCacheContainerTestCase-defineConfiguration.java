@Test public void defineConfiguration(){
  ConfigurationBuilder builder=new ConfigurationBuilder();
  Configuration config=builder.build();
  when(this.manager.defineConfiguration("other",config)).thenReturn(config);
  Configuration result=this.subject.defineConfiguration("other",config);
  assertSame(config,result);
}
