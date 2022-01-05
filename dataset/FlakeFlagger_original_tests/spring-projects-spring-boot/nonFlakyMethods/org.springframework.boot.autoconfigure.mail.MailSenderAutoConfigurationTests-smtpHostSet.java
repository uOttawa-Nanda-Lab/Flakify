@Test public void smtpHostSet(){
  String host="192.168.1.234";
  load(EmptyConfig.class,"spring.mail.host:" + host);
  JavaMailSenderImpl bean=(JavaMailSenderImpl)this.context.getBean(JavaMailSender.class);
  assertEquals(host,bean.getHost());
}
