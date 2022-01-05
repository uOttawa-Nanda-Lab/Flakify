@Test public void directoryOfFilesWithPrefix(){
  List<String> urls=ResourceUtils.getUrls("file:src/test/resources/dir-sample/code/*",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
