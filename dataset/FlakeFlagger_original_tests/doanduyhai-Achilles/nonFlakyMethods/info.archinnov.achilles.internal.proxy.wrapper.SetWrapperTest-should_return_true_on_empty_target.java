@Test public void should_return_true_on_empty_target() throws Exception {
  SetWrapper wrapper=prepareSetWrapper(new HashSet<>());
  assertThat(wrapper.isEmpty()).isTrue();
}
