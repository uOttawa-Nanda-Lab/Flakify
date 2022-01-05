@Test public void jarFileAlwaysMatches() throws Exception {
  ResourceMatcher resourceMatcher=new ResourceMatcher(Arrays.asList("*"),Arrays.asList("**/*.jar"));
  List<MatchedResource> found=resourceMatcher.find(Arrays.asList(new File("src/test/resources/templates"),new File("src/test/resources/foo.jar")));
  FooJarMatcher matcher=new FooJarMatcher(MatchedResource.class);
  assertThat(found,hasItem(matcher));
  assertTrue(matcher.getMatched().isRoot());
}
