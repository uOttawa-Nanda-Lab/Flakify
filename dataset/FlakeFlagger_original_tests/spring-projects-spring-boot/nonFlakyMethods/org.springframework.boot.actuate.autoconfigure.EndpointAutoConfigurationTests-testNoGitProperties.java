@Test public void testNoGitProperties() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.git.properties:classpath:nonexistent");
  this.context.register(EndpointAutoConfiguration.class);
  this.context.refresh();
  InfoEndpoint endpoint=this.context.getBean(InfoEndpoint.class);
  assertNotNull(endpoint);
  assertNull(endpoint.invoke().get("git"));
}
