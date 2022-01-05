@Test public void should_not_mark_dirty_on_clear_when_empty() throws Exception {
  List<Object> target=new ArrayList<>();
  ListWrapper wrapper=prepareListWrapper(target);
  wrapper.clear();
  assertThat(dirtyMap).isEmpty();
}
