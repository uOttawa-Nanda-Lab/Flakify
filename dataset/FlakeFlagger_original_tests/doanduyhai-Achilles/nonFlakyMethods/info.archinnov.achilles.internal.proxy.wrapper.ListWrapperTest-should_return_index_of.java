@Test public void should_return_index_of() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b");
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("b")).thenReturn("b");
  assertThat(listWrapper.indexOf("b")).isEqualTo(1);
}
