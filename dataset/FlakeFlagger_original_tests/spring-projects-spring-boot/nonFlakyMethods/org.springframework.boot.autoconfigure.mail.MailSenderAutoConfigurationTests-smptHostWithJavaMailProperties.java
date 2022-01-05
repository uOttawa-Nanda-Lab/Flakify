@Test public void smptHostWithJavaMailProperties(){
  load(EmptyConfig.class,"spring.mail.host:localhost","spring.mail.properties.mail.smtp.auth:true");
  JavaMailSenderImpl bean=(JavaMailSenderImpl)this.context.getBean(JavaMailSender.class);
  assertEquals("true",bean.getJavaMailProperties().get("mail.smtp.auth"));
}
