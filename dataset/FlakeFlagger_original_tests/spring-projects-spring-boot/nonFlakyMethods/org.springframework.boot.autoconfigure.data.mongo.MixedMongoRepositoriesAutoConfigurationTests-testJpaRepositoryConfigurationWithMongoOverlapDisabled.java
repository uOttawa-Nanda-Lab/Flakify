@Test public void testJpaRepositoryConfigurationWithMongoOverlapDisabled() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false","spring.data.mongo.repositories.enabled:false");
  this.context.register(OverlapConfiguration.class,BaseConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CityRepository.class));
}
