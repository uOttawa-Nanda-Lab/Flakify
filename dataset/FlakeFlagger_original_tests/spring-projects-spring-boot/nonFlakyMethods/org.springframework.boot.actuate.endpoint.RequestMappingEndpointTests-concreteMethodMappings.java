@Test public void concreteMethodMappings(){
  EndpointHandlerMapping mapping=new EndpointHandlerMapping(Arrays.asList(new EndpointMvcAdapter(new DumpEndpoint())));
  mapping.setApplicationContext(new StaticApplicationContext());
  mapping.afterPropertiesSet();
  this.endpoint.setMethodMappings(Collections.<AbstractHandlerMethodMapping<?>>singletonList(mapping));
  Map<String,Object> result=this.endpoint.invoke();
  assertEquals(1,result.size());
  assertTrue(result.keySet().iterator().next().contains("/dump"));
  @SuppressWarnings("unchecked") Map<String,Object> handler=(Map<String,Object>)result.values().iterator().next();
  assertTrue(handler.containsKey("method"));
}
