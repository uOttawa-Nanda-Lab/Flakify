/** 
 * Process: Null arguemnt for annotation repository
 * @throws Throwable throwable exception
 */
@Test(expected=ValidateException.class) public void testProcessNullAnnotationRepository() throws Throwable {
  annotations.process(null,null,null);
}
