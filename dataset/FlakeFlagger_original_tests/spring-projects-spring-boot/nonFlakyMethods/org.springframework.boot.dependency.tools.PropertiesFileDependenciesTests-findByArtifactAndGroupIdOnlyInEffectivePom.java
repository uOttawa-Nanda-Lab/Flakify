@Test public void findByArtifactAndGroupIdOnlyInEffectivePom() throws Exception {
  assertThat(this.dependencies.find("org.extra","extra01"),nullValue());
}
