@Test public void smtpHostNotSet(){
  load(EmptyConfig.class);
  assertEquals(0,this.context.getBeansOfType(JavaMailSender.class).size());
}
