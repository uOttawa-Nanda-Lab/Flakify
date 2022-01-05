@Test public void testNoRepositoryConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(EmptyConfiguration.class,ElasticsearchAutoConfiguration.class,ElasticsearchRepositoriesAutoConfiguration.class,ElasticsearchDataAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(Client.class));
}
