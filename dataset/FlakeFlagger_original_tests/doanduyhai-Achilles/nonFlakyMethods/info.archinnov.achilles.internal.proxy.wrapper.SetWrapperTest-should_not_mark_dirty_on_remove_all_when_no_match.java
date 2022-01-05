@Test public void should_not_mark_dirty_on_remove_all_when_no_match() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b","c");
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.removeAll(Arrays.asList("d","e"));
  assertThat(dirtyMap).isEmpty();
}
