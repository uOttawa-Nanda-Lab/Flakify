@Test public void should_return_true_on_contains_all() throws Exception {
  SetWrapper wrapper=prepareSetWrapper(Sets.<Object>newHashSet("a","b","c","d"));
  List<Object> check=Arrays.<Object>asList("a","c");
  when(proxifier.removeProxy(check)).thenReturn(check);
  assertThat(wrapper.containsAll(check)).isTrue();
}
