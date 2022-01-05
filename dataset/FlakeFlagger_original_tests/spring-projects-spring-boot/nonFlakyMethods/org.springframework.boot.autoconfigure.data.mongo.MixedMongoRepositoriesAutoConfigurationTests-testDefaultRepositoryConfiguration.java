@Test public void testDefaultRepositoryConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  this.context.register(TestConfiguration.class,BaseConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CountryRepository.class));
}
