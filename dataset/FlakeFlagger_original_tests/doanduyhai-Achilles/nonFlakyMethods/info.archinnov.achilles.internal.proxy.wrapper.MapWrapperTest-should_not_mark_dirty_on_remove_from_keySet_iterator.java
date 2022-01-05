@Test public void should_not_mark_dirty_on_remove_from_keySet_iterator() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Iterator<Object> keyIterator=wrapper.keySet().iterator();
  keyIterator.next();
  keyIterator.remove();
  assertThat(dirtyMap).isEmpty();
}
