@Test public void should_not_mark_dirty_on_iterator_remove() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b","c");
  SetWrapper wrapper=prepareSetWrapper(target);
  Iterator<Object> iteratorWrapper=wrapper.iterator();
  iteratorWrapper.next();
  iteratorWrapper.remove();
  assertThat(dirtyMap).isEmpty();
}
