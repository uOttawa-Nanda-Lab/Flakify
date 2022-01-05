@Test(expected=XMLStreamException.class) public void testParseSubsystemWithBadChild() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + Namespace.CURRENT.getUriString() + "\"><invalid/></subsystem>";
  super.parse(subsystemXml);
}
