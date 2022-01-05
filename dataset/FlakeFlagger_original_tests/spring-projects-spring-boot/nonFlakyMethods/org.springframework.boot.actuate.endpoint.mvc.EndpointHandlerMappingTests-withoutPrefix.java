@Test public void withoutPrefix() throws Exception {
  TestMvcEndpoint endpointA=new TestMvcEndpoint(new TestEndpoint("/a"));
  TestMvcEndpoint endpointB=new TestMvcEndpoint(new TestEndpoint("/b"));
  EndpointHandlerMapping mapping=new EndpointHandlerMapping(Arrays.asList(endpointA,endpointB));
  mapping.setApplicationContext(this.context);
  mapping.afterPropertiesSet();
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/a")).getHandler(),equalTo((Object)new HandlerMethod(endpointA,this.method)));
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/b")).getHandler(),equalTo((Object)new HandlerMethod(endpointB,this.method)));
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/c")),nullValue());
}
