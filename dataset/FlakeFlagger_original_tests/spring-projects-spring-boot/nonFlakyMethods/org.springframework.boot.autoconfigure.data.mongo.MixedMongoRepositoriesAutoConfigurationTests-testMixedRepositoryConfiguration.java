@Test public void testMixedRepositoryConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  this.context.register(MixedConfiguration.class,BaseConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CountryRepository.class));
  assertNotNull(this.context.getBean(CityRepository.class));
}
