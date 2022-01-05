@Test public void testCustomNamingStrategyViaJpaProperties() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jpa.properties.hibernate.ejb.naming_strategy:" + "org.hibernate.cfg.EJB3NamingStrategy");
  setupTestConfiguration();
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean bean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  String actual=(String)bean.getJpaPropertyMap().get("hibernate.ejb.naming_strategy");
  assertThat(actual,not(equalTo("org.hibernate.cfg.EJB3NamingStrategy")));
}
