@Test public void testParseSubsystemWithBadAttribute() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + JacORBSubsystemParser.Namespace.CURRENT.getUriString() + "\" bad=\"very_bad\">"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad attribute");
  }
 catch (  XMLStreamException expected) {
  }
}
