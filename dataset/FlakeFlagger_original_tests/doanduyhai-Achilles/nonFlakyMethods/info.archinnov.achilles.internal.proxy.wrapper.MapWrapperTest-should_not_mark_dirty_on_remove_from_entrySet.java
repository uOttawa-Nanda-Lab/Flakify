@Test public void should_not_mark_dirty_on_remove_from_entrySet() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Set<Entry<Object,Object>> entrySet=wrapper.entrySet();
  Entry<Integer,String> entry=target.entrySet().iterator().next();
  when(proxifier.removeProxy((Object)entry)).thenReturn(entry);
  entrySet.remove(entry);
  assertThat(dirtyMap).isEmpty();
}
