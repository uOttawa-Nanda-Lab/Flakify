@Test public void duplicateResource() throws Exception {
  URLClassLoader loader=new URLClassLoader(new URL[]{new URL("file:./src/test/resources/"),new File("src/test/resources/").getAbsoluteFile().toURI().toURL()});
  List<String> urls=ResourceUtils.getUrls("classpath:init.groovy",loader);
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
