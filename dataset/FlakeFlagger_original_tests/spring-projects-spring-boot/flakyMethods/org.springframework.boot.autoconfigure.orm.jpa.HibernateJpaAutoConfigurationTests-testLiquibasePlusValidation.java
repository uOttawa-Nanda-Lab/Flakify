@Test public void testLiquibasePlusValidation() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false","liquibase.changeLog:classpath:db/changelog/db.changelog-city.yaml","spring.jpa.hibernate.ddl-auto:validate");
  setupTestConfiguration();
  this.context.register(LiquibaseAutoConfiguration.class);
  this.context.refresh();
}
