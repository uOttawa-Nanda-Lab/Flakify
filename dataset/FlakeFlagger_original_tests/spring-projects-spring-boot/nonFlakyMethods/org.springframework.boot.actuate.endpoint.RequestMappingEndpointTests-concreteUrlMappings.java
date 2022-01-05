@Test public void concreteUrlMappings(){
  SimpleUrlHandlerMapping mapping=new SimpleUrlHandlerMapping();
  mapping.setUrlMap(Collections.singletonMap("/foo",new Object()));
  mapping.setApplicationContext(new StaticApplicationContext());
  mapping.initApplicationContext();
  this.endpoint.setHandlerMappings(Collections.<AbstractUrlHandlerMapping>singletonList(mapping));
  Map<String,Object> result=this.endpoint.invoke();
  assertEquals(1,result.size());
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)result.get("/foo");
  assertEquals("java.lang.Object",map.get("type"));
}
