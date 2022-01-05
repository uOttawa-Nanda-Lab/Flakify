@Test public void testStop() throws Exception {
  JpaInitializer jpaInitializer=new JpaInitializer(persistService);
  jpaInitializer.stop();
  verify(persistService).stop();
  Method method=JpaInitializer.class.getMethod("stop");
  Dispose dispose=method.getAnnotation(Dispose.class);
  assertEquals(10,dispose.order());
}
