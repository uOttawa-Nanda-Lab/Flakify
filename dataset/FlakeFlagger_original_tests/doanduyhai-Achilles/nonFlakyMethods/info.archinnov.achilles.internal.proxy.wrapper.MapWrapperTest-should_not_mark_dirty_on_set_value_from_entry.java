@Test public void should_not_mark_dirty_on_set_value_from_entry() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Set<Entry<Object,Object>> entrySet=wrapper.entrySet();
  entrySet.iterator().next().setValue("sdfsd");
  assertThat(dirtyMap).isEmpty();
}
