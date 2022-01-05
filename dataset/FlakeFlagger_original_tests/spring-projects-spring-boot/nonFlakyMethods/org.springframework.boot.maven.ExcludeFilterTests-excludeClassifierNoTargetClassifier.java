@Test public void excludeClassifierNoTargetClassifier() throws ArtifactFilterException {
  ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar","jdk5")));
  Artifact artifact=createArtifact("com.foo","bar");
  Set result=filter.filter(Collections.singleton(artifact));
  assertEquals("Should not have been filtered",1,result.size());
  assertSame(artifact,result.iterator().next());
}
