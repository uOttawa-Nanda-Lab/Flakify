@Test public void should_not_mark_dirty_when_clear_on_empty_map() throws Exception {
  Map<Integer,String> target=prepareMap();
  target.clear();
  MapWrapper wrapper=prepareMapWrapper(target);
  wrapper.clear();
  assertThat(dirtyMap).isEmpty();
}
