/** 
 * check that evaluator with bogus exp does not start
 * @throws Exception
 */
@Test public void testBogusExp1(){
  jee.setExpression("mzzzz.get(\"key\").equals(null)");
  jee.setName("bogus");
  jee.start();
  assertFalse(jee.isStarted());
}
