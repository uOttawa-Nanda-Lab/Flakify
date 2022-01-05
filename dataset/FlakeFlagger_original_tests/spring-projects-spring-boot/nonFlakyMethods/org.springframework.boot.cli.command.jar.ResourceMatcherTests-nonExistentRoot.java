@Test public void nonExistentRoot() throws IOException {
  ResourceMatcher resourceMatcher=new ResourceMatcher(Arrays.asList("alpha/**","bravo/*","*"),Arrays.asList(".*","alpha/**/excluded"));
  List<MatchedResource> matchedResources=resourceMatcher.find(Arrays.asList(new File("does-not-exist")));
  assertEquals(0,matchedResources.size());
}
