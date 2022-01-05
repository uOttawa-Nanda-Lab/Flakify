@Test public void findArtifactId() throws Exception {
  this.managedDependencies.find("artifactId");
  verify(this.delegate).find("artifactId");
}
