@Test public void testDefaultRepositoryConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,ElasticsearchAutoConfiguration.class,ElasticsearchRepositoriesAutoConfiguration.class,ElasticsearchDataAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CityRepository.class));
  assertNotNull(this.context.getBean(Client.class));
}
