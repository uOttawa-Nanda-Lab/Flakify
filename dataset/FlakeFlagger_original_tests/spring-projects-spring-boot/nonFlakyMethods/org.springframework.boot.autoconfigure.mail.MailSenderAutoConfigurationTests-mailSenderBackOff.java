@Test public void mailSenderBackOff(){
  load(ManualMailConfiguration.class,"spring.mail.host:smtp.acme.org","spring.mail.user:user","spring.mail.password:secret");
  JavaMailSenderImpl bean=(JavaMailSenderImpl)this.context.getBean(JavaMailSender.class);
  assertNull(bean.getUsername());
  assertNull(bean.getPassword());
}
