@Test public void withPrefix() throws Exception {
  TestMvcEndpoint endpointA=new TestMvcEndpoint(new TestEndpoint("/a"));
  TestMvcEndpoint endpointB=new TestMvcEndpoint(new TestEndpoint("/b"));
  EndpointHandlerMapping mapping=new EndpointHandlerMapping(Arrays.asList(endpointA,endpointB));
  mapping.setApplicationContext(this.context);
  mapping.setPrefix("/a");
  mapping.afterPropertiesSet();
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/a/a")).getHandler(),equalTo((Object)new HandlerMethod(endpointA,this.method)));
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/a/b")).getHandler(),equalTo((Object)new HandlerMethod(endpointB,this.method)));
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/a")),nullValue());
}
