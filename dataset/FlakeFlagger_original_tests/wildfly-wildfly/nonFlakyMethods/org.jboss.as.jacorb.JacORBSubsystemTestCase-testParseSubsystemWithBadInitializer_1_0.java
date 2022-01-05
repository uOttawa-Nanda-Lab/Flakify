@Test public void testParseSubsystemWithBadInitializer_1_0() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + JacORBSubsystemParser.Namespace.JacORB_1_0.getUriString() + "\">"+ "   <initializers>invalid</initializers>"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad initializer");
  }
 catch (  XMLStreamException expected) {
  }
}
