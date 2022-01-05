@Test public void excludeClassifier() throws ArtifactFilterException {
  ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar","jdk5")));
  Set result=filter.filter(Collections.singleton(createArtifact("com.foo","bar","jdk5")));
  assertEquals("Should have been filtered",0,result.size());
}
