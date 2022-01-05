@Test public void should_contain_key() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  assertThat(wrapper.containsKey(1)).isTrue();
}
