/** 
 * see http://code.alibabatech.com/jira/browse/DUBBO-571
 */
@Test public void test_getDeclaredMethodNames_ContainExtendsParentMethods() throws Exception {
  assertArrayEquals(new String[]{"hello"},Wrapper.getWrapper(Parent1.class).getMethodNames());
  assertArrayEquals(new String[]{},Wrapper.getWrapper(Son.class).getDeclaredMethodNames());
}
