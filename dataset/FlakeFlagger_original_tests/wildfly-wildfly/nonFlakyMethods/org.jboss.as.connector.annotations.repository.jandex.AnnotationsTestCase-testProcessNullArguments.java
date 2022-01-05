/** 
 * Process: Null arguemnts
 * @throws Throwable throwable exception
 */
@Test(expected=ValidateException.class) public void testProcessNullArguments() throws Throwable {
  annotations.process(null,null,null);
}
