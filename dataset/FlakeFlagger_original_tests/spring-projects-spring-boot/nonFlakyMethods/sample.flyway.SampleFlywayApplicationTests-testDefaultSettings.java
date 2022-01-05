@Test public void testDefaultSettings() throws Exception {
  assertEquals(new Integer(1),this.template.queryForObject("SELECT COUNT(*) from PERSON",Integer.class));
}
