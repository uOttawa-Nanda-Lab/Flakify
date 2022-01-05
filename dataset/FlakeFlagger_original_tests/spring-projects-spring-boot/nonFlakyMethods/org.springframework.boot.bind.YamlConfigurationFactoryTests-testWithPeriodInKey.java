@Test public void testWithPeriodInKey() throws Exception {
  Jee jee=createJee("mymap:\n  ? key1.key2\n  : value");
  assertEquals("value",jee.mymap.get("key1.key2"));
}
