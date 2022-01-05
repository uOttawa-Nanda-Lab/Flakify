@Test public void shouldFindNoResourcesWhenNoneExist() throws IOException {
  final ThreadLocal<Collection<String>> filenameListHolder=new ThreadLocal<Collection<String>>();
  final UriLocator uriLocator=createJarLocator(filenameListHolder);
  uriLocator.locate("classpath:com/**.js");
  final Collection<String> filenameList=filenameListHolder.get();
  Assert.assertNotNull(filenameList);
  Assert.assertTrue(filenameList.isEmpty());
}
