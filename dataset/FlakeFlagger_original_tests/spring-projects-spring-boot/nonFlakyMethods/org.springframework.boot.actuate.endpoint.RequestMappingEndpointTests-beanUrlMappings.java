@Test public void beanUrlMappings(){
  StaticApplicationContext context=new StaticApplicationContext();
  SimpleUrlHandlerMapping mapping=new SimpleUrlHandlerMapping();
  mapping.setUrlMap(Collections.singletonMap("/foo",new Object()));
  mapping.setApplicationContext(context);
  mapping.initApplicationContext();
  context.getDefaultListableBeanFactory().registerSingleton("mapping",mapping);
  this.endpoint.setApplicationContext(context);
  Map<String,Object> result=this.endpoint.invoke();
  assertEquals(1,result.size());
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)result.get("/foo");
  assertEquals("mapping",map.get("bean"));
}
