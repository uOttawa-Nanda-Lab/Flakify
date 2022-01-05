@Test public void testDefaultDdlAutoForMySql() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.driverClassName:com.mysql.jdbc.Driver","spring.datasource.url:jdbc:mysql://localhost/nonexistent","spring.datasource.initialize:false","spring.jpa.database:MYSQL");
  this.context.register(TestConfiguration.class,DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,HibernateJpaAutoConfiguration.class);
  this.context.refresh();
  JpaProperties bean=this.context.getBean(JpaProperties.class);
  DataSource dataSource=this.context.getBean(DataSource.class);
  String actual=bean.getHibernateProperties(dataSource).get("hibernate.hbm2ddl.auto");
  assertThat(actual,nullValue());
}
