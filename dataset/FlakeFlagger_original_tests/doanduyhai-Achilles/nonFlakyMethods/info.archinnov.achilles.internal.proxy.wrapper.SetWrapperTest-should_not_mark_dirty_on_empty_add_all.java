@Test public void should_not_mark_dirty_on_empty_add_all() throws Exception {
  Set<Object> target=new HashSet<>();
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.addAll(new HashSet<>());
  assertThat(target).hasSize(0);
  assertThat(dirtyMap).isEmpty();
}
