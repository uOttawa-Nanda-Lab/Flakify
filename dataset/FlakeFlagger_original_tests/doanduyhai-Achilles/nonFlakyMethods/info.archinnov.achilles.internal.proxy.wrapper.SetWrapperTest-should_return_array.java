@Test public void should_return_array() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b","c");
  SetWrapper wrapper=prepareSetWrapper(target);
  when(propertyMeta.type()).thenReturn(PropertyType.LIST);
  assertThat(wrapper.toArray()).contains("a","b","c");
}
