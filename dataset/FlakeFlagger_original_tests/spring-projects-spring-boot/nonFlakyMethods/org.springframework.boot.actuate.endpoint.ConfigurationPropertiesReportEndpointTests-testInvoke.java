@Test public void testInvoke() throws Exception {
  assertThat(getEndpointBean().invoke().size(),greaterThan(0));
}
