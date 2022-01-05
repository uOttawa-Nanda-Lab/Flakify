@Test public void should_contain_value() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  when(proxifier.removeProxy("FR")).thenReturn("FR");
  assertThat(wrapper.containsValue("FR")).isTrue();
}
