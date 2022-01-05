@Test public void should_not_mark_dirty_on_empty_add_all() throws Exception {
  List<Object> target=new ArrayList<>();
  ListWrapper wrapper=prepareListWrapper(target);
  wrapper.addAll(new HashSet<>());
  assertThat(target).hasSize(0);
  assertThat(dirtyMap).isEmpty();
}
