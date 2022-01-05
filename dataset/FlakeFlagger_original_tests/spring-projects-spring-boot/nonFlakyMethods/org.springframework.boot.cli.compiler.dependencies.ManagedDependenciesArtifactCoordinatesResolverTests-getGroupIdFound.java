@Test public void getGroupIdFound() throws Exception {
  assertThat(this.resolver.getGroupId("a1"),equalTo("g1"));
}
