@Test public void testCustomNamingStrategy() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jpa.hibernate.namingStrategy:" + "org.hibernate.cfg.EJB3NamingStrategy");
  setupTestConfiguration();
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean bean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  String actual=(String)bean.getJpaPropertyMap().get("hibernate.ejb.naming_strategy");
  assertThat(actual,equalTo("org.hibernate.cfg.EJB3NamingStrategy"));
}
