@Test public void excludedWins() throws Exception {
  ResourceMatcher resourceMatcher=new ResourceMatcher(Arrays.asList("*"),Arrays.asList("**/*.jar"));
  List<MatchedResource> found=resourceMatcher.find(Arrays.asList(new File("src/test/resources")));
  assertThat(found,not(hasItem(new FooJarMatcher(MatchedResource.class))));
}
