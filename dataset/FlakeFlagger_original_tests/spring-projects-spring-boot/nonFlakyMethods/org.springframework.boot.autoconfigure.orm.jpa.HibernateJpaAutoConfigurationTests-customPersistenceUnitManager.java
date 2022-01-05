@Test public void customPersistenceUnitManager() throws Exception {
  setupTestConfiguration(TestConfigurationWithCustomPersistenceUnitManager.class);
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Field field=LocalContainerEntityManagerFactoryBean.class.getDeclaredField("persistenceUnitManager");
  field.setAccessible(true);
  assertThat(field.get(entityManagerFactoryBean),equalTo((Object)this.context.getBean(PersistenceUnitManager.class)));
}
