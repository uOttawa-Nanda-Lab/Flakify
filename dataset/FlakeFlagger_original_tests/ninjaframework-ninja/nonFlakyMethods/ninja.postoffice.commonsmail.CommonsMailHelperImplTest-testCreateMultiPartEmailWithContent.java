@Test public void testCreateMultiPartEmailWithContent() throws Exception {
  Mail mail=new MailImpl();
  mail.setBodyText("simple body text");
  MultiPartEmail multiPartEmail=commonsmailHelper.createMultiPartEmailWithContent(mail);
  assertTrue(multiPartEmail instanceof MultiPartEmail);
  mail=new MailImpl();
  mail.setBodyHtml("<br>simple body text<br>");
  multiPartEmail=commonsmailHelper.createMultiPartEmailWithContent(mail);
  assertTrue(multiPartEmail instanceof HtmlEmail);
  mail=new MailImpl();
  mail.setBodyText("simple body text");
  mail.setBodyHtml("<br>simple body text<br>");
  multiPartEmail=commonsmailHelper.createMultiPartEmailWithContent(mail);
  assertTrue(multiPartEmail instanceof HtmlEmail);
}
