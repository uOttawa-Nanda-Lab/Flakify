@Test public void filterDependencies() throws MojoExecutionException {
  TestableDependencyFilterMojo mojo=new TestableDependencyFilterMojo(Collections.<Exclude>emptyList(),"com.foo","exclude-id");
  Artifact artifact=createArtifact("com.bar","one");
  Set<Artifact> artifacts=mojo.filterDependencies(createArtifact("com.foo","one"),createArtifact("com.foo","two"),createArtifact("com.bar","exclude-id"),artifact);
  assertEquals("wrong filtering of artifacts",1,artifacts.size());
  assertSame("Wrong filtered artifact",artifact,artifacts.iterator().next());
}
