@Test public void should_not_mark_dirty_on_collection_remove() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Collection<Object> collectionWrapper=wrapper.values();
  when(proxifier.removeProxy("FR")).thenReturn("FR");
  collectionWrapper.remove("FR");
  assertThat(dirtyMap).isEmpty();
}
