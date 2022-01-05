@Test public void testAttachments(){
  RpcContext context=RpcContext.getContext();
  Map<String,String> map=new HashMap<String,String>();
  map.put("_11","1111");
  map.put("_22","2222");
  map.put(".33","3333");
  context.setAttachments(map);
  Assert.assertEquals(map,context.getAttachments());
  Assert.assertEquals("1111",context.getAttachment("_11"));
  context.setAttachment("_11","11.11");
  Assert.assertEquals("11.11",context.getAttachment("_11"));
  context.setAttachment(null,"22222");
  context.setAttachment("_22",null);
  Assert.assertEquals("22222",context.getAttachment(null));
  Assert.assertNull(context.getAttachment("_22"));
  Assert.assertNull(context.getAttachment("_33"));
  Assert.assertEquals("3333",context.getAttachment(".33"));
  context.clearAttachments();
  Assert.assertNull(context.getAttachment("_11"));
}
