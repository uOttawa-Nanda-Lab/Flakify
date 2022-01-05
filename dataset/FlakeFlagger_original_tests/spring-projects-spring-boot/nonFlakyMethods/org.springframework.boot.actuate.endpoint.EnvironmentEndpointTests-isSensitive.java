@Test public void isSensitive() throws Exception {
  assertThat(getEndpointBean().isSensitive(),equalTo(this.sensitive));
}
