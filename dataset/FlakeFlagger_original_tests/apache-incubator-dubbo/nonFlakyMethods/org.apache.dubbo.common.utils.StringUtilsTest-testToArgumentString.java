@Test public void testToArgumentString() throws Exception {
  String s=StringUtils.toArgumentString(new Object[]{"a",0,Collections.singletonMap("enabled",true)});
  assertThat(s,containsString("a,"));
  assertThat(s,containsString("0,"));
  assertThat(s,containsString("{\"enabled\":true}"));
}
