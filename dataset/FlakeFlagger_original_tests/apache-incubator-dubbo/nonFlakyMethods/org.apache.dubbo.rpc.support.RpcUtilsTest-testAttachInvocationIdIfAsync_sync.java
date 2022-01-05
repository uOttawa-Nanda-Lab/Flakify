/** 
 * scenario: sync invocation, no attachment added by default verify: no id attribute added in attachment
 */
@Test public void testAttachInvocationIdIfAsync_sync(){
  URL url=URL.valueOf("dubbo://localhost/");
  Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{});
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  assertNull(RpcUtils.getInvocationId(inv));
}
