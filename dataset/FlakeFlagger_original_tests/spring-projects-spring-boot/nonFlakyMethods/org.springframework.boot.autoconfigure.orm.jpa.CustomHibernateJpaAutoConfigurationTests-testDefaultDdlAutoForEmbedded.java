@Test public void testDefaultDdlAutoForEmbedded() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  this.context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,HibernateJpaAutoConfiguration.class);
  this.context.refresh();
  JpaProperties bean=this.context.getBean(JpaProperties.class);
  DataSource dataSource=this.context.getBean(DataSource.class);
  String actual=bean.getHibernateProperties(dataSource).get("hibernate.hbm2ddl.auto");
  assertThat(actual,equalTo("create-drop"));
}
