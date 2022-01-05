@Test public void testCommonsMailer() throws Exception {
  Mail mail=MailImplTestHelper.getMailImplWithDemoContent();
  CommonsmailHelper commonsmailHelper=new CommonsmailHelperImpl();
  Postoffice postoffice=new PostofficeCommonsmailImpl(commonsmailHelper,"localhost",SMTP_TEST_PORT,false,null,null,false);
  postoffice.send(mail);
  assertEquals("from1@domain",((InternetAddress)greenMail.getReceivedMessages()[0].getFrom()[0]).getAddress());
  assertEquals("subject",greenMail.getReceivedMessages()[0].getSubject());
}
