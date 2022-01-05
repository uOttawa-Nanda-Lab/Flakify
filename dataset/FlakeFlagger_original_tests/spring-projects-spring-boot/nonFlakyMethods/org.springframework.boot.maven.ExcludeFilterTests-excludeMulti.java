@Test public void excludeMulti() throws ArtifactFilterException {
  ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar"),createExclude("com.foo","bar2"),createExclude("org.acme","app")));
  Set<Artifact> artifacts=new HashSet<Artifact>();
  artifacts.add(createArtifact("com.foo","bar"));
  artifacts.add(createArtifact("com.foo","bar"));
  Artifact anotherAcme=createArtifact("org.acme","another-app");
  artifacts.add(anotherAcme);
  Set result=filter.filter(artifacts);
  assertEquals("Two dependencies should have been filtered",1,result.size());
  assertSame(anotherAcme,result.iterator().next());
}
