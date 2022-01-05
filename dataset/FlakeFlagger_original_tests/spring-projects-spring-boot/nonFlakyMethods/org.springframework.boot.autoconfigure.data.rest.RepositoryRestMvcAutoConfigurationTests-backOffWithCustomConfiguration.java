@Test public void backOffWithCustomConfiguration(){
  load(TestConfigurationWithRestMvcConfig.class,"spring.data.rest.baseUri:foo");
  assertNotNull(this.context.getBean(RepositoryRestMvcConfiguration.class));
  RepositoryRestConfiguration bean=this.context.getBean(RepositoryRestConfiguration.class);
  assertEquals("Custom base URI should not have been set",URI.create(""),bean.getBaseUri());
}
