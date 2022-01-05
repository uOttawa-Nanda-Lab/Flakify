@Test public void testGeneralizePersons() throws Exception {
  Object persons=new Person[]{new Person(),new Person()};
  Object o=PojoUtils.generalize(persons);
  assertTrue(o instanceof Object[]);
  assertEquals(((Object[])o).length,2);
}
