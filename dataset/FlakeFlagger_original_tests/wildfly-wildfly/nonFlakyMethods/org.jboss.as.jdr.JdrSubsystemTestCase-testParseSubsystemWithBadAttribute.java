@Test(expected=XMLStreamException.class) public void testParseSubsystemWithBadAttribute() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + Namespace.CURRENT.getUriString() + "\" attr=\"wrong\"/>";
  super.parse(subsystemXml);
}
