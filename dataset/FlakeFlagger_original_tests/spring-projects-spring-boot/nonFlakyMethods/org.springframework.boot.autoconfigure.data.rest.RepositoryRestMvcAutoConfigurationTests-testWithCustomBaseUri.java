@Test public void testWithCustomBaseUri() throws Exception {
  load(TestConfiguration.class,"spring.data.rest.baseUri:foo");
  assertNotNull(this.context.getBean(RepositoryRestMvcConfiguration.class));
  RepositoryRestConfiguration bean=this.context.getBean(RepositoryRestConfiguration.class);
  URI expectedUri=URI.create("foo");
  assertEquals("Custom baseURI not set",expectedUri,bean.getBaseUri());
  BaseUri baseUri=this.context.getBean(BaseUri.class);
  assertEquals("Custom baseUri has not been applied to BaseUri bean",expectedUri,baseUri.getUri());
}
