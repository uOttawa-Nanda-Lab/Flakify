@Test public void should_not_mark_dirty_on_clear_when_empty() throws Exception {
  Set<Object> target=new HashSet<>();
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.clear();
  assertThat(dirtyMap).isEmpty();
}
