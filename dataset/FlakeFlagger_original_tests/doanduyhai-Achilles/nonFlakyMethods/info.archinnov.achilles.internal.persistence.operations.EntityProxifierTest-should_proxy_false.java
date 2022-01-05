@Test public void should_proxy_false() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(1L).buid();
  assertThat(proxifier.isProxy(bean)).isFalse();
}
