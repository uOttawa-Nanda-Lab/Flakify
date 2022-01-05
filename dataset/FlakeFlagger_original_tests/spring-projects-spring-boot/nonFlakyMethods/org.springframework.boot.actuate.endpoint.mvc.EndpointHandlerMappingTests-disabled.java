@Test public void disabled() throws Exception {
  TestMvcEndpoint endpoint=new TestMvcEndpoint(new TestEndpoint("/a"));
  EndpointHandlerMapping mapping=new EndpointHandlerMapping(Arrays.asList(endpoint));
  mapping.setDisabled(true);
  mapping.setApplicationContext(this.context);
  mapping.afterPropertiesSet();
  assertThat(mapping.getHandler(new MockHttpServletRequest("GET","/a")),nullValue());
}
