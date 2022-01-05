@Test public void testJpaRepositoryConfigurationWithMongoTemplate() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  this.context.register(JpaConfiguration.class,BaseConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CityRepository.class));
}
