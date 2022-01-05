@Test public void should_not_mark_dirty_on_remove_non_existing_from_entrySet() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Set<Entry<Object,Object>> entrySet=wrapper.entrySet();
  Entry<Object,Object> entry=new AbstractMap.SimpleEntry<Object,Object>(4,"csdf");
  entrySet.remove(entry);
  assertThat(dirtyMap).isEmpty();
}
