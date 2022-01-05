/** 
 * Test that in DEPLOYMENT mode if  {@link InvalidGroupNameException} is thrown, the response redirect to 404.
 */
@Test public void testInvalidGroupNameExceptionThrownInDEPLOYMENTMode() throws Exception {
  testChainContinueWhenSpecificExceptionThrown(new InvalidGroupNameException(""));
}
