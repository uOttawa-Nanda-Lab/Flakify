@Test public void should_get_value() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  assertThat(wrapper.get(1)).isEqualTo("FR");
}
