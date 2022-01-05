@Test(expected=HttpRequestMethodNotSupportedException.class) public void onlyGetHttpMethodForNonActionEndpoints() throws Exception {
  TestActionEndpoint endpoint=new TestActionEndpoint(new TestEndpoint("/a"));
  EndpointHandlerMapping mapping=new EndpointHandlerMapping(Arrays.asList(endpoint));
  mapping.setApplicationContext(this.context);
  mapping.afterPropertiesSet();
  assertNotNull(mapping.getHandler(new MockHttpServletRequest("GET","/a")));
  assertNull(mapping.getHandler(new MockHttpServletRequest("POST","/a")));
}
