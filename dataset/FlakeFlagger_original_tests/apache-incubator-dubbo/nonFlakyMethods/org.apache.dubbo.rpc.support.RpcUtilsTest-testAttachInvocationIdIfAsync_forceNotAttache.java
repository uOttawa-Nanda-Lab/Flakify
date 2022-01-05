/** 
 * scenario: explicitly configure to not add attachment verify: no id attribute added in attachment
 */
@Test public void testAttachInvocationIdIfAsync_forceNotAttache(){
  URL url=URL.valueOf("dubbo://localhost/?test.async=true&" + Constants.AUTO_ATTACH_INVOCATIONID_KEY + "=false");
  Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{});
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  assertNull(RpcUtils.getInvocationId(inv));
}
