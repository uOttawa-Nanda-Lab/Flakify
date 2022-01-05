/** 
 * see http://code.alibabatech.com/jira/browse/DUBBO-571
 */
@Test public void test_getMethodNames_ContainExtendsParentMethods() throws Exception {
  assertArrayEquals(new String[]{"hello","world"},Wrapper.getWrapper(Son.class).getMethodNames());
}
