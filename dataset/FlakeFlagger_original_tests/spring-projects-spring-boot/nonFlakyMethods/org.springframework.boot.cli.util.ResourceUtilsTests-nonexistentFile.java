@Test public void nonexistentFile(){
  List<String> urls=ResourceUtils.getUrls("file:nonexistent.groovy",null);
  assertEquals(0,urls.size());
}
