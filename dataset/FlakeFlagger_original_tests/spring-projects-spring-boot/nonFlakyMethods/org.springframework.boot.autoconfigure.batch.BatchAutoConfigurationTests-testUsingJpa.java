@Test public void testUsingJpa() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,HibernateJpaAutoConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  PlatformTransactionManager transactionManager=this.context.getBean(PlatformTransactionManager.class);
  assertTrue(transactionManager.toString().contains("JpaTransactionManager"));
  assertNotNull(this.context.getBean(EntityManagerFactory.class));
  assertNull(this.context.getBean(JobRepository.class).getLastJobExecution("job",new JobParameters()));
}
