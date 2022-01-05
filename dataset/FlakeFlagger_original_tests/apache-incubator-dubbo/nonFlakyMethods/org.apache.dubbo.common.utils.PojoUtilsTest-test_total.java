@Test public void test_total() throws Exception {
  Object generalize=PojoUtils.generalize(bigPerson);
  Type gtype=getType("returnBigPersonMethod");
  Object realize=PojoUtils.realize(generalize,BigPerson.class,gtype);
  assertEquals(bigPerson,realize);
}
