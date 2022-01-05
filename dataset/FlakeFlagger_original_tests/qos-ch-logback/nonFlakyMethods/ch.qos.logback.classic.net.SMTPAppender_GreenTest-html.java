@Test public void html() throws Exception {
  String subject="html";
  buildSMTPAppender(subject,SYNCHRONOUS);
  smtpAppender.setAsynchronousSending(false);
  smtpAppender.setLayout(buildHTMLLayout());
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  logger.debug("html");
  logger.error("en error",new Exception("an exception"));
  MimeMultipart mp=verifyAndExtractMimeMultipart(subject);
  SAXReader reader=new SAXReader();
  reader.setValidation(true);
  reader.setEntityResolver(new XHTMLEntityResolver());
  byte[] messageBytes=getAsByteArray(mp.getBodyPart(0).getInputStream());
  ByteArrayInputStream bais=new ByteArrayInputStream(messageBytes);
  try {
    reader.read(bais);
  }
 catch (  DocumentException de) {
    System.out.println("incoming message:");
    System.out.println(new String(messageBytes));
    throw de;
  }
}
