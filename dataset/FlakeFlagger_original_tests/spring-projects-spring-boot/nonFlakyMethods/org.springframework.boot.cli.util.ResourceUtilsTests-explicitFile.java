@Test public void explicitFile(){
  List<String> urls=ResourceUtils.getUrls("file:src/test/resources/init.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
