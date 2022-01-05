@Test public void findByArtifactIdMissing() throws Exception {
  assertThat(this.dependencies.find("missing"),nullValue());
}
