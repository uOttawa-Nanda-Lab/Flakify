/** 
 * scenario: explicitly configure to add attachment verify: id attribute added in attachment
 */
@Test public void testAttachInvocationIdIfAsync_forceAttache(){
  URL url=URL.valueOf("dubbo://localhost/?" + Constants.AUTO_ATTACH_INVOCATIONID_KEY + "=true");
  Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{});
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  assertNotNull(RpcUtils.getInvocationId(inv));
}
