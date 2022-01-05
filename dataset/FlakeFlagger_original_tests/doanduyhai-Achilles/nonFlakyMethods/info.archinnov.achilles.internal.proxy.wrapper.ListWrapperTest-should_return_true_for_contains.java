@Test public void should_return_true_for_contains() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  assertThat(listWrapper.contains("a")).isTrue();
}
