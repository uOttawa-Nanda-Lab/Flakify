@Test public void directoryOfFiles(){
  List<String> urls=ResourceUtils.getUrls("src/test/resources/dir-sample/code/*",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
