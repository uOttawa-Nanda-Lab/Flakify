@Test public void should_create_resources_once() throws Exception {
  AchillesInternalCQLResource resource=new AchillesInternalCQLResource();
  assertThat(resource.getPersistenceManagerFactory()).isSameAs(pmf);
  assertThat(resource.getPersistenceManager()).isSameAs(manager);
  assertThat(resource.getNativeSession()).isSameAs(session);
}
