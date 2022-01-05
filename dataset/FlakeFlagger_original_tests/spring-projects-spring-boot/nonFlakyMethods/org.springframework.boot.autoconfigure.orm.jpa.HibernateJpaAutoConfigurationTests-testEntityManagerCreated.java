@Test public void testEntityManagerCreated() throws Exception {
  setupTestConfiguration();
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertNotNull(this.context.getBean(JpaTransactionManager.class));
}
