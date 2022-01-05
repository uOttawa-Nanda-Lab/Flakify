/** 
 * AS7-2720 tests for parsing particular XA-datasource in standalone mode
 * @throws Exception
 */
@Test public void testAddComplexXaDsUsername() throws Exception {
  testAddComplexXaDs(ConnectionSecurityType.USER_PASSWORD);
}
