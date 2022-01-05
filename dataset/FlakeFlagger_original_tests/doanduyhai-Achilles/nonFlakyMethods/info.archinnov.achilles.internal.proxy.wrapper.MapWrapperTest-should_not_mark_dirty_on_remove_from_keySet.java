@Test public void should_not_mark_dirty_on_remove_from_keySet() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Set<Object> keySet=wrapper.keySet();
  when(proxifier.removeProxy(1)).thenReturn(1);
  keySet.remove(1);
  assertThat(dirtyMap).isEmpty();
}
