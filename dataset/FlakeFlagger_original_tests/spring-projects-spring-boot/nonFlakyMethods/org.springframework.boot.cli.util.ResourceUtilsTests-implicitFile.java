@Test public void implicitFile(){
  List<String> urls=ResourceUtils.getUrls("src/test/resources/init.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
