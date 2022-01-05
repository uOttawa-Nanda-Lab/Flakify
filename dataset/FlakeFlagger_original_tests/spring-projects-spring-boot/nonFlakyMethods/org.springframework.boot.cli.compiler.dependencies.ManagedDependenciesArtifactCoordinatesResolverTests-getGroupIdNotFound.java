@Test public void getGroupIdNotFound() throws Exception {
  assertThat(this.resolver.getGroupId("a2"),nullValue());
}
