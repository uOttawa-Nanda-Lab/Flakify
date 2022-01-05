@Test public void doesNotTriggerDefaultRepositoryDetectionIfCustomized(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(CustomizedConfiguration.class,ElasticsearchAutoConfiguration.class,ElasticsearchRepositoriesAutoConfiguration.class,ElasticsearchDataAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CityElasticsearchDbRepository.class));
}
