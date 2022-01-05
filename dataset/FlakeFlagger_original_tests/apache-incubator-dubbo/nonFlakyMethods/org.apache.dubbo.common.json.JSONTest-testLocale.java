@Test public void testLocale() throws Exception {
  Locale obj=Locale.US;
  String str=JSON.json(obj);
  assertEquals("\"en_US\"",str);
  assertEquals(obj,JSON.parse(str,Locale.class));
}
