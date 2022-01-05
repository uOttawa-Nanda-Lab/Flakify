@Test public void invoke() throws Exception {
  assertThat(getEndpointBean().invoke().size(),greaterThan(0));
}
