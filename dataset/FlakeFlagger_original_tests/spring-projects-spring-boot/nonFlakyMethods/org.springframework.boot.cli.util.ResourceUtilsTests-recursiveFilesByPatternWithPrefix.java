@Test public void recursiveFilesByPatternWithPrefix(){
  List<String> urls=ResourceUtils.getUrls("file:src/test/resources/dir-sample/**/*.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
