@Test public void should_return_last_index_of() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","a");
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  assertThat(listWrapper.lastIndexOf("a")).isEqualTo(2);
}
