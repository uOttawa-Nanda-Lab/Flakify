/** 
 * scenario: async invocation, add attachment by default verify: no error report when the original attachment is null
 */
@Test public void testAttachInvocationIdIfAsync_nullAttachments(){
  URL url=URL.valueOf("dubbo://localhost/?test.async=true");
  Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{});
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  assertTrue(RpcUtils.getInvocationId(inv) >= 0l);
}
