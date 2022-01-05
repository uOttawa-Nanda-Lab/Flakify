@Test public void smptHostWithSettings(){
  String host="192.168.1.234";
  load(EmptyConfig.class,"spring.mail.host:" + host,"spring.mail.port:42","spring.mail.username:john","spring.mail.password:secret","spring.mail.default-encoding:ISO-9");
  JavaMailSenderImpl bean=(JavaMailSenderImpl)this.context.getBean(JavaMailSender.class);
  assertEquals(host,bean.getHost());
  assertEquals(42,bean.getPort());
  assertEquals("john",bean.getUsername());
  assertEquals("secret",bean.getPassword());
  assertEquals("ISO-9",bean.getDefaultEncoding());
}
