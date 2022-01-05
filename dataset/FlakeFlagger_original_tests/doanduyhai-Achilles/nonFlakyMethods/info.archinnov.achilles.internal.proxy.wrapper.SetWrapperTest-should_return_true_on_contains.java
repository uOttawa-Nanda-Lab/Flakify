@Test public void should_return_true_on_contains() throws Exception {
  SetWrapper wrapper=prepareSetWrapper(Sets.<Object>newHashSet("a","b"));
  when(proxifier.removeProxy("a")).thenReturn("a");
  assertThat(wrapper.contains("a")).isTrue();
}
