@Test public void excludeArtifactIdNoMatch() throws ArtifactFilterException {
  ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar")));
  Artifact artifact=createArtifact("com.foo","biz");
  Set result=filter.filter(Collections.singleton(artifact));
  assertEquals("Should not have been filtered",1,result.size());
  assertSame(artifact,result.iterator().next());
}
