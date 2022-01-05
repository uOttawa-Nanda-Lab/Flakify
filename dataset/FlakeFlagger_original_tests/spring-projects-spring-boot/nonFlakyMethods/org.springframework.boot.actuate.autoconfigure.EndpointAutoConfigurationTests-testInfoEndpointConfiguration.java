@Test public void testInfoEndpointConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"info.foo:bar");
  this.context.register(EndpointAutoConfiguration.class);
  this.context.refresh();
  InfoEndpoint endpoint=this.context.getBean(InfoEndpoint.class);
  assertNotNull(endpoint);
  assertNotNull(endpoint.invoke().get("git"));
  assertEquals("bar",endpoint.invoke().get("foo"));
}
