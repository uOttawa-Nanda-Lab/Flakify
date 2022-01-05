@Test public void testParseSubsystemWithBadChild() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + Namespace.CURRENT.getUriString() + "\">"+ "   <invalid/>"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad child");
  }
 catch (  XMLStreamException expected) {
  }
  subsystemXml="<subsystem xmlns=\"" + Namespace.CURRENT.getUriString() + "\">"+ "    <orb>"+ "        <poa/>"+ "    </orb>"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad child");
  }
 catch (  XMLStreamException expected) {
  }
}
