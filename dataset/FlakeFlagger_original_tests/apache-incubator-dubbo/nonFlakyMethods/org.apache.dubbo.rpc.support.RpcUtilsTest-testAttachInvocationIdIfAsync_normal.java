/** 
 * regular scenario: async invocation in URL verify: 1. whether invocationId is set correctly, 2. idempotent or not
 */
@Test public void testAttachInvocationIdIfAsync_normal(){
  URL url=URL.valueOf("dubbo://localhost/?test.async=true");
  Map<String,String> attachments=new HashMap<String,String>();
  attachments.put("aa","bb");
  Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{},attachments);
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  long id1=RpcUtils.getInvocationId(inv);
  RpcUtils.attachInvocationIdIfAsync(url,inv);
  long id2=RpcUtils.getInvocationId(inv);
  assertTrue(id1 == id2);
  assertTrue(id1 >= 0);
  assertEquals("bb",attachments.get("aa"));
}
