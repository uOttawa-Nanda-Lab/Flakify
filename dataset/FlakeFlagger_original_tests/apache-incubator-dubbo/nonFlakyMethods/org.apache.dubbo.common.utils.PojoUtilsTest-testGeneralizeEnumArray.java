@Test public void testGeneralizeEnumArray() throws Exception {
  Object days=new Enum[]{Day.FRIDAY,Day.SATURDAY};
  Object o=PojoUtils.generalize(days);
  assertTrue(o instanceof String[]);
  assertEquals(((String[])o)[0],"FRIDAY");
  assertEquals(((String[])o)[1],"SATURDAY");
}
