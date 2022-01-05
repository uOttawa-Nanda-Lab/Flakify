@Test public void should_return_true_on_is_empty() throws Exception {
  List<Object> target=asList();
  ListWrapper listWrapper=prepareListWrapper(target);
  assertThat(listWrapper.isEmpty()).isTrue();
}
