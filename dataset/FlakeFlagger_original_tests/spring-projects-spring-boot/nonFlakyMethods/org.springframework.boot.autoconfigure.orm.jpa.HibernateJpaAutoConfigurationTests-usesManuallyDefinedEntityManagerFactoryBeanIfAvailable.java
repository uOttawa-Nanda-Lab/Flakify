@Test public void usesManuallyDefinedEntityManagerFactoryBeanIfAvailable(){
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  setupTestConfiguration(TestConfigurationWithEntityManagerFactory.class);
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean factoryBean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Map<String,Object> map=factoryBean.getJpaPropertyMap();
  assertThat(map.get("configured"),equalTo((Object)"manually"));
}
