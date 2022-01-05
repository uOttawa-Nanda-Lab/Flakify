@Test public void should_not_mark_dirty_on_element_add() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a");
  SetWrapper wrapper=prepareSetWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  wrapper.add("a");
  assertThat(dirtyMap).isEmpty();
}
