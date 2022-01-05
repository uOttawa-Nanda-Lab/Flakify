/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testConfiguration() throws Throwable {
  setup();
  MultipleAdminObject1 adminObject1=(MultipleAdminObject1)context.lookup("after/Name3");
  assertNotNull("AO1 not found",adminObject1);
}
