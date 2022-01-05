@Test public void invoke() throws Exception {
  assertThat(getEndpointBean().invoke().get("a"),equalTo((Object)0.5f));
}
