@Test public void testIsPojo() throws Exception {
  assertFalse(PojoUtils.isPojo(boolean.class));
  assertFalse(PojoUtils.isPojo(Map.class));
  assertFalse(PojoUtils.isPojo(List.class));
  assertTrue(PojoUtils.isPojo(Person.class));
}
