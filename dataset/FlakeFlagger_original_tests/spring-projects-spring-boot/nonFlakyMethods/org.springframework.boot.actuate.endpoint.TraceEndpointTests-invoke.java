@Test public void invoke() throws Exception {
  Trace trace=getEndpointBean().invoke().get(0);
  assertThat(trace.getInfo().get("a"),equalTo((Object)"b"));
}
