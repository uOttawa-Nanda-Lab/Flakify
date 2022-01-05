@Test public void testGetProperties1() throws Exception {
  try {
    System.setProperty(Constants.DUBBO_PROPERTIES_KEY,"properties.load");
    Properties p=ConfigUtils.getProperties();
    assertThat((String)p.get("a"),equalTo("12"));
    assertThat((String)p.get("b"),equalTo("34"));
    assertThat((String)p.get("c"),equalTo("56"));
  }
  finally {
    System.clearProperty(Constants.DUBBO_PROPERTIES_KEY);
  }
}
