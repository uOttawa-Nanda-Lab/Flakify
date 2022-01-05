@Test public void findGroupIdArtifactId() throws Exception {
  this.managedDependencies.find("groupId","artifactId");
  verify(this.delegate).find("groupId","artifactId");
}
