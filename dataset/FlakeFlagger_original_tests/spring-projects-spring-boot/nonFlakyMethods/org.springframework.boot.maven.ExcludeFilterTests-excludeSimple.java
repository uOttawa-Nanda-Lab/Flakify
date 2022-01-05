@Test public void excludeSimple() throws ArtifactFilterException {
  ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar")));
  Set result=filter.filter(Collections.singleton(createArtifact("com.foo","bar")));
  assertEquals("Should have been filtered",0,result.size());
}
