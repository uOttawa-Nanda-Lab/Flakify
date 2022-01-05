@Test public void invoke() throws Exception {
  assertThat(getEndpointBean().invoke().get("a"),equalTo((Object)"b"));
}
