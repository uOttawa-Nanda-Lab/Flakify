@Test public void should_create_resources_once() throws Exception {
  AchillesResource resource=new AchillesResource(null,"info.archinnov.achilles.junit.test.entity");
  assertThat(resource.getPersistenceManagerFactory()).isSameAs(pmf);
  assertThat(resource.getPersistenceManager()).isSameAs(manager);
  assertThat(resource.getNativeSession()).isSameAs(session);
}
