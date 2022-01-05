@Test public void getId() throws Exception {
  assertThat(getEndpointBean().getId(),equalTo(this.id));
}
