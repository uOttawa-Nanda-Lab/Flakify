@Test public void noDataSource() throws Exception {
  registerAndRefresh(FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  assertEquals(0,this.context.getBeanNamesForType(Flyway.class).length);
}
