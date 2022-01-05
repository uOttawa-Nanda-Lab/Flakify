@Test public void testLoadResources() throws IOException {
  ClassLoader tempClassLoader=factory.createNewTempClassLoader();
  String resource=TestEntity.class.getName().replace('.','/') + ".class";
  assertNotNull(tempClassLoader.getResource(resource));
  assertTrue(tempClassLoader.getResources(resource).hasMoreElements());
  InputStream resourceStream=tempClassLoader.getResourceAsStream(resource);
  assertNotNull(resourceStream);
  resourceStream.close();
}
