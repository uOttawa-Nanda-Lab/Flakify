@Test public void testParseSubsystemWithBadChild() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + JacORBSubsystemParser.Namespace.CURRENT.getUriString() + "\">"+ "   <invalid/>"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad child");
  }
 catch (  XMLStreamException expected) {
  }
  subsystemXml="<subsystem xmlns=\"urn:jboss:domain:jacorb:1.0\">" + "    <orb name=\"JBoss\" print-version=\"off\">" + "        <poa/>"+ "    </orb>"+ "</subsystem>";
  try {
    super.parse(subsystemXml);
    Assert.fail("Should not have parsed bad child");
  }
 catch (  XMLStreamException expected) {
  }
}
