@Test public void should_not_mark_dirty_on_remove_when_no_match() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b");
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.remove("c");
  assertThat(dirtyMap).isEmpty();
}
