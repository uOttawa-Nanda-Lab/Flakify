@Test public void should_initialize_list_of_entities() throws Exception {
  List<CompleteBean> entities=Arrays.asList(entity);
  when(facade.initialize(entity)).thenReturn(entity);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  List<CompleteBean> actual=manager.initialize(entities);
  assertThat(actual).containsExactly(entity);
}
