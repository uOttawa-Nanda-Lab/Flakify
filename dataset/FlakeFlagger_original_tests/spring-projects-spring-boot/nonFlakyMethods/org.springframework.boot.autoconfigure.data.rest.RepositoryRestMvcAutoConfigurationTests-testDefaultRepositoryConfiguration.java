@Test public void testDefaultRepositoryConfiguration() throws Exception {
  load(TestConfiguration.class);
  assertNotNull(this.context.getBean(RepositoryRestMvcConfiguration.class));
}
