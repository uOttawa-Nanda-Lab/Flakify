/** 
 * Test for bug ACT-10: whitespaces/newlines in expressions lead to exceptions
 */
@Deployment public void testWhitespaceInExpression(){
  runtimeService.startProcessInstanceByKey("inclusiveWhiteSpaceInExpression",CollectionUtil.singletonMap("input",1));
}
