@Test public void should_initialize_list() throws Exception {
  List<CompleteBean> actual=facade.initialize(asList(entity));
  assertThat(actual).containsExactly(entity);
  verify(initializer).initializeEntity(entity,meta);
}
