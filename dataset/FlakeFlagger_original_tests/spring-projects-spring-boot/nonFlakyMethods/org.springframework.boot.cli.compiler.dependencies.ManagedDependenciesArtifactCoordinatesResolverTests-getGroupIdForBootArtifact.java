@Test public void getGroupIdForBootArtifact() throws Exception {
  assertThat(this.resolver.getGroupId("spring-boot-something"),equalTo("org.springframework.boot"));
  verify(this.dependencies,never()).find(anyString());
}
