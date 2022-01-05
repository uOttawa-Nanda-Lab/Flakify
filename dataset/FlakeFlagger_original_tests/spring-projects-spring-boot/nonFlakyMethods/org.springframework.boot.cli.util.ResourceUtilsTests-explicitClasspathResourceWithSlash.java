@Test public void explicitClasspathResourceWithSlash(){
  List<String> urls=ResourceUtils.getUrls("classpath:/init.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
