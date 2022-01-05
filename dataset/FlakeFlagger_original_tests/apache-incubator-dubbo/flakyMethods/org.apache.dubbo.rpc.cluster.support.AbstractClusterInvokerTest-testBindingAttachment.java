@Test public void testBindingAttachment(){
  final String attachKey="attach";
  final String attachValue="value";
  RpcContext.getContext().setAttachment(attachKey,attachValue);
  Map<String,String> attachments=RpcContext.getContext().getAttachments();
  Assert.assertTrue("set attachment failed!",attachments != null && attachments.size() == 1);
  cluster=new AbstractClusterInvoker(dic){
    @Override protected Result doInvoke(    Invocation invocation,    List invokers,    LoadBalance loadbalance) throws RpcException {
      String value=invocation.getAttachment(attachKey);
      Assert.assertTrue("binding attachment failed!",value != null && value.equals(attachValue));
      return null;
    }
  }
;
  cluster.invoke(invocation);
}
