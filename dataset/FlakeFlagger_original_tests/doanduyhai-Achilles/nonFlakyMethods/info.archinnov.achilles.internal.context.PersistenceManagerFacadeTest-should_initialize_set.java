@Test public void should_initialize_set() throws Exception {
  Set<CompleteBean> actual=facade.initialize(Sets.newHashSet(entity));
  assertThat(actual).containsExactly(entity);
  verify(initializer).initializeEntity(entity,meta);
}
