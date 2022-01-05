/** 
 * Test config properties
 * @throws Throwable Thrown if case of an error
 */
@Test public void testConfigProperties() throws Throwable {
  Context ctx=new InitialContext();
  ConfigPropertyConnectionFactory connectionFactory=(ConfigPropertyConnectionFactory)ctx.lookup(CF_JNDI_NAME);
  assertNotNull(connectionFactory);
  ConfigPropertyAdminObjectInterface adminObject=(ConfigPropertyAdminObjectInterface)ctx.lookup(AO_JNDI_NAME);
  assertNotNull(adminObject);
  ConfigPropertyConnection connection=connectionFactory.getConnection();
  assertNotNull(connection);
  assertEquals("A",connection.getResourceAdapterProperty());
  assertEquals("B",connection.getManagedConnectionFactoryProperty());
  assertEquals("C",adminObject.getProperty());
  connection.close();
}
