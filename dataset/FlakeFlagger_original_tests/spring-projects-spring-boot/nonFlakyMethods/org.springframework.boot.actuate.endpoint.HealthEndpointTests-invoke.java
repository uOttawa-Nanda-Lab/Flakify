@Test public void invoke() throws Exception {
  assertThat(getEndpointBean().invoke().getStatus(),equalTo(Status.UNKNOWN));
}
