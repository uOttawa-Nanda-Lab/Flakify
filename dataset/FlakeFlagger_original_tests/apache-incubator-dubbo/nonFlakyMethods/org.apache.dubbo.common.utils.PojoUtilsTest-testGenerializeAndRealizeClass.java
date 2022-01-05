@Test public void testGenerializeAndRealizeClass() throws Exception {
  Object generalize=PojoUtils.generalize(Integer.class);
  assertEquals(Integer.class.getName(),generalize);
  Object real=PojoUtils.realize(generalize,Integer.class.getClass());
  assertEquals(Integer.class,real);
  generalize=PojoUtils.generalize(int[].class);
  assertEquals(int[].class.getName(),generalize);
  real=PojoUtils.realize(generalize,int[].class.getClass());
  assertEquals(int[].class,real);
}
