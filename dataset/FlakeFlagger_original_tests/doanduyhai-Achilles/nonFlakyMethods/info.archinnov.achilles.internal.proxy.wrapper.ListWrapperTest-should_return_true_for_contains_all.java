@Test public void should_return_true_for_contains_all() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper listWrapper=prepareListWrapper(target);
  assertThat(listWrapper.containsAll(asList("a","c"))).isTrue();
}
