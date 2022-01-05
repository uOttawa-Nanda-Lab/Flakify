@Test public void filterGroupIdExactMatch() throws MojoExecutionException {
  TestableDependencyFilterMojo mojo=new TestableDependencyFilterMojo(Collections.<Exclude>emptyList(),"com.foo","");
  Artifact artifact=createArtifact("com.foo.bar","one");
  Set<Artifact> artifacts=mojo.filterDependencies(createArtifact("com.foo","one"),createArtifact("com.foo","two"),artifact);
  assertEquals("wrong filtering of artifacts",1,artifacts.size());
  assertSame("Wrong filtered artifact",artifact,artifacts.iterator().next());
}
