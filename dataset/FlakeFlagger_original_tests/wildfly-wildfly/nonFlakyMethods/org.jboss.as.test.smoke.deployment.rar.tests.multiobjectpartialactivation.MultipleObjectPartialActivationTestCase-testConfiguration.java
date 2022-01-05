/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testConfiguration() throws Throwable {
  assertNotNull("CF1 not found",connectionFactory1);
  assertNotNull("AO1 not found",adminObject1);
}
