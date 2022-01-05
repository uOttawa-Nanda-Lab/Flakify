@Test public void testFlywayPlusValidation() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false","flyway.locations:classpath:db/city","spring.jpa.hibernate.ddl-auto:validate");
  setupTestConfiguration();
  this.context.register(FlywayAutoConfiguration.class);
  this.context.refresh();
}
