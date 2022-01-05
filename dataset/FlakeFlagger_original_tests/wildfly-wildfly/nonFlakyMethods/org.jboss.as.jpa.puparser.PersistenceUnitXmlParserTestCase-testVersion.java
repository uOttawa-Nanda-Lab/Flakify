/** 
 * See http://issues.jboss.org/browse/STXM-8
 */
@Test public void testVersion() throws Exception {
  final String persistence_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> " + "<persistence xmlns=\"http://java.sun.com/xml/ns/persistence\" version=\"1.0\">" + "  <persistence-unit name=\"mypc\">"+ "    <description>Persistence Unit."+ "    </description>"+ "    <jta-data-source>java:/H2DS</jta-data-source>"+ "    <class>org.jboss.as.test.integration.jpa.epcpropagation.MyEntity</class>"+ "    <properties> <property name=\"hibernate.hbm2ddl.auto\" value=\"create-drop\"/></properties>"+ "  </persistence-unit>"+ "</persistence>";
  XMLStreamReader reader=XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(persistence_xml));
  PersistenceUnitMetadataHolder metadataHolder=PersistenceUnitXmlParser.parse(reader,PropertyReplacers.noop());
  PersistenceUnitMetadata metadata=metadataHolder.getPersistenceUnits().get(0);
  String version=metadata.getPersistenceXMLSchemaVersion();
  assertEquals("1.0",version);
}
