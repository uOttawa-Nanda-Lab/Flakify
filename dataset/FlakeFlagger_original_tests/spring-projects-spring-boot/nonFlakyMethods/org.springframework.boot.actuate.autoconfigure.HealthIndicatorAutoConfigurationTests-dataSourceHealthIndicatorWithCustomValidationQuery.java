@Test public void dataSourceHealthIndicatorWithCustomValidationQuery(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(PropertyPlaceholderAutoConfiguration.class,DataSourceProperties.class,DataSourceConfig.class,DataSourcePoolMetadataProvidersConfiguration.class,HealthIndicatorAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.validation-query:SELECT from FOOBAR","health.diskspace.enabled:false");
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  HealthIndicator healthIndicator=beans.values().iterator().next();
  assertEquals(DataSourceHealthIndicator.class,healthIndicator.getClass());
  DataSourceHealthIndicator dataSourceHealthIndicator=(DataSourceHealthIndicator)healthIndicator;
  assertEquals("SELECT from FOOBAR",dataSourceHealthIndicator.getQuery());
}
