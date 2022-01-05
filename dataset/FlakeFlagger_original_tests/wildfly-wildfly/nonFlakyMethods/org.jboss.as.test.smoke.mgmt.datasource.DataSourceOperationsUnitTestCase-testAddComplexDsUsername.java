/** 
 * AS7-2720 tests for parsing particular datasource in standalone mode
 * @throws Exception
 */
@Test public void testAddComplexDsUsername() throws Exception {
  testAddComplexDs(ConnectionSecurityType.USER_PASSWORD);
}
