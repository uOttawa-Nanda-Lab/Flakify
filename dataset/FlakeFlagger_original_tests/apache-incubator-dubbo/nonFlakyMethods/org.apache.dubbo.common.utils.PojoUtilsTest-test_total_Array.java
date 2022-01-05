@Test public void test_total_Array() throws Exception {
  Object[] persons=new Object[]{bigPerson,bigPerson,bigPerson};
  Object generalize=PojoUtils.generalize(persons);
  Object[] realize=(Object[])PojoUtils.realize(generalize,Object[].class);
  assertArrayEquals(persons,realize);
}
