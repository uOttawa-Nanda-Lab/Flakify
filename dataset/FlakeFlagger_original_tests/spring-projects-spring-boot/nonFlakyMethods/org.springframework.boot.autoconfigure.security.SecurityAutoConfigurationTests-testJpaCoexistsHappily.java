@Test public void testJpaCoexistsHappily() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.url:jdbc:hsqldb:mem:testsecdb");
  this.context.register(EntityConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,SecurityAutoConfiguration.class,ServerPropertiesAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JpaTransactionManager.class));
}
