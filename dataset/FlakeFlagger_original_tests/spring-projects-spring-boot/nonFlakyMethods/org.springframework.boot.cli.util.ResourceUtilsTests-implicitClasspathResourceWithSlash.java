@Test public void implicitClasspathResourceWithSlash(){
  List<String> urls=ResourceUtils.getUrls("/init.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
