@Test public void should_get_element_at_index() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper wrapper=prepareListWrapper(target);
  assertThat(wrapper.get(1)).isEqualTo("b");
}
