@Test public void findByArtifactAndGroupIdMissing() throws Exception {
  assertThat(this.dependencies.find("org.sample","missing"),nullValue());
}
