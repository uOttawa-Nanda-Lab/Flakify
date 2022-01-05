@Test public void should_return_size() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b","c");
  SetWrapper wrapper=prepareSetWrapper(target);
  assertThat(wrapper.size()).isEqualTo(3);
}
