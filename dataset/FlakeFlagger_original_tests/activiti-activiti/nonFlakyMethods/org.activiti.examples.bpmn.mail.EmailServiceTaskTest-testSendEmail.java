@Deployment public void testSendEmail() throws Exception {
  String from="ordershipping@activiti.org";
  boolean male=true;
  String recipientName="John Doe";
  String recipient="johndoe@alfresco.com";
  Date now=new Date();
  String orderId="123456";
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("sender",from);
  vars.put("recipient",recipient);
  vars.put("recipientName",recipientName);
  vars.put("male",male);
  vars.put("now",now);
  vars.put("orderId",orderId);
  runtimeService.startProcessInstanceByKey("sendMailExample",vars);
  List<WiserMessage> messages=wiser.getMessages();
  assertEquals(1,messages.size());
  WiserMessage message=messages.get(0);
  MimeMessage mimeMessage=message.getMimeMessage();
  assertEquals("Your order " + orderId + " has been shipped",mimeMessage.getHeader("Subject",null));
  assertEquals(from,mimeMessage.getHeader("From",null));
  assertTrue(mimeMessage.getHeader("To",null).contains(recipient));
}
