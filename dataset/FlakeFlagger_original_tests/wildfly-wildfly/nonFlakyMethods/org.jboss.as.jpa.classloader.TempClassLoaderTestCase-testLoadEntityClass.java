@Test public void testLoadEntityClass() throws Exception {
  ClassLoader tempClassLoader=factory.createNewTempClassLoader();
  String className=TestEntity.class.getName();
  Class<?> entityClass=tempClassLoader.loadClass(className);
  Object entity=entityClass.newInstance();
  assertFalse(entityClass.equals(TestEntity.class));
  assertFalse(entity instanceof TestEntity);
  assertTrue(entity.getClass().isAnnotationPresent(Entity.class));
  assertTrue(entityClass == tempClassLoader.loadClass(className));
}
