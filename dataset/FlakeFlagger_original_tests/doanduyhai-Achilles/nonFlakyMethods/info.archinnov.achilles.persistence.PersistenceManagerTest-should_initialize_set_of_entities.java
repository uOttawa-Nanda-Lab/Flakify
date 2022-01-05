@Test public void should_initialize_set_of_entities() throws Exception {
  Set<CompleteBean> entities=Sets.newHashSet(entity);
  when(facade.initialize(entity)).thenReturn(entity);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  Set<CompleteBean> actual=manager.initialize(entities);
  assertThat(actual).containsExactly(entity);
}
