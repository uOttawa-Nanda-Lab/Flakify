@Test public void should_get_encoded_map_changes() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  when(pm.encode("a")).thenReturn("a");
  Map<Object,Object> actual=changeSet.getEncodedMapChanges();
  assertThat(actual).contains(entry(1,"a"));
}
