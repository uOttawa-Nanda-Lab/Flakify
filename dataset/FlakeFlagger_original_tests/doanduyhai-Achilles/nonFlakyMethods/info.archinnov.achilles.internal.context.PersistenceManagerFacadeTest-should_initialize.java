@Test public void should_initialize() throws Exception {
  CompleteBean actual=facade.initialize(entity);
  assertThat(actual).isSameAs(entity);
  verify(initializer).initializeEntity(entity,meta);
}
