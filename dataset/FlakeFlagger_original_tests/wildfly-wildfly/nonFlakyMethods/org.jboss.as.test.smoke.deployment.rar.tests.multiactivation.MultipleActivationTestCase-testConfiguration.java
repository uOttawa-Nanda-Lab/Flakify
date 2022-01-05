/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testConfiguration() throws Throwable {
  assertNotNull("CF1 not found",connectionFactory1);
  assertNotNull("CF2 not found",connectionFactory2);
  assertNotNull("AO1 not found",adminObject1);
  assertNotNull("AO2 not found",adminObject2);
  assertEquals("not equal AOs",adminObject1,adminObject2);
}
