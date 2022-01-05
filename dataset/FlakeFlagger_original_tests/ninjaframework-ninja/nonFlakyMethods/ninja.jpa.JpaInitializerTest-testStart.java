@Test public void testStart() throws Exception {
  JpaInitializer jpaInitializer=new JpaInitializer(persistService);
  jpaInitializer.start();
  verify(persistService).start();
  Method method=JpaInitializer.class.getMethod("start");
  Start start=method.getAnnotation(Start.class);
  assertEquals(10,start.order());
}
