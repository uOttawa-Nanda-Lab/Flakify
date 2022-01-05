@Test public void nonexistentClasspathResource(){
  List<String> urls=ResourceUtils.getUrls("classpath:nonexistent.groovy",null);
  assertEquals(0,urls.size());
}
