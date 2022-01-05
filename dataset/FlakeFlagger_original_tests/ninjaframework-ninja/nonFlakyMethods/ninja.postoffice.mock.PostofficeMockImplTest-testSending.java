@Test public void testSending() throws Exception {
  Postoffice postoffice=new PostofficeMockImpl();
  Mail firstMail=new MailImpl();
  firstMail.setSubject("first mail");
  firstMail.addTo("to@localhost");
  firstMail.setFrom("from@localhost");
  firstMail.setBodyText("simple body text");
  assertEquals(null,((PostofficeMockImpl)postoffice).getLastSentMail());
  postoffice.send(firstMail);
  assertEquals("first mail",((PostofficeMockImpl)postoffice).getLastSentMail().getSubject());
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getTos().contains("to@localhost"));
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getFrom().equals("from@localhost"));
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getBodyText().equals("simple body text"));
  Mail secondMail=new MailImpl();
  secondMail.setSubject("second mail");
  secondMail.addTo("to@localhost");
  secondMail.setFrom("from@localhost");
  secondMail.setBodyText("simple body text");
  postoffice.send(secondMail);
  assertEquals("second mail",((PostofficeMockImpl)postoffice).getLastSentMail().getSubject());
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getTos().contains("to@localhost"));
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getFrom().equals("from@localhost"));
  assertTrue(((PostofficeMockImpl)postoffice).getLastSentMail().getBodyText().equals("simple body text"));
}
