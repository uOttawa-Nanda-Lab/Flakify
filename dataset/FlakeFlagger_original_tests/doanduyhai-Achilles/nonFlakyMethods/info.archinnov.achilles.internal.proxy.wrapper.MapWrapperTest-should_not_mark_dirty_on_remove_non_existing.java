@Test public void should_not_mark_dirty_on_remove_non_existing() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  wrapper.remove(10);
  assertThat(dirtyMap).isEmpty();
}
