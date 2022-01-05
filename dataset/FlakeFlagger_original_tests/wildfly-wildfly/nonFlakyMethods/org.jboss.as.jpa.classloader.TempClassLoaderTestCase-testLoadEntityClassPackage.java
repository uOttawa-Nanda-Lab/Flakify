@Test public void testLoadEntityClassPackage() throws Exception {
  ClassLoader tempClassLoader=factory.createNewTempClassLoader();
  String className=TestEntity.class.getName();
  Class<?> entityClass=tempClassLoader.loadClass(className);
  assertNotNull("could not load package for entity class that came from NewTempClassLoader",entityClass.getPackage());
}
