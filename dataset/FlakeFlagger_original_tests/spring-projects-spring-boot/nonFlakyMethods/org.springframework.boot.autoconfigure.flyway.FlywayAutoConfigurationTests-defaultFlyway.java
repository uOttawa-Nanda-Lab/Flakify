@Test public void defaultFlyway() throws Exception {
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  Flyway flyway=this.context.getBean(Flyway.class);
  assertEquals("[classpath:db/migration]",Arrays.asList(flyway.getLocations()).toString());
}
