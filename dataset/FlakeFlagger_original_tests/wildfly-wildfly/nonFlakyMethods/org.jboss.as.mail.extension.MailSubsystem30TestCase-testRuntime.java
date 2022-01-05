@Test public void testRuntime() throws Exception {
  KernelServicesBuilder builder=createKernelServicesBuilder(new DefaultInitializer()).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  if (!mainServices.isSuccessfulBoot()) {
    Assert.fail(mainServices.getBootError().toString());
  }
  ServiceController<?> javaMailService=mainServices.getContainer().getService(MailSessionDefinition.SESSION_CAPABILITY.getCapabilityServiceName("defaultMail"));
  javaMailService.setMode(ServiceController.Mode.ACTIVE);
  Session session=(Session)javaMailService.getValue();
  Assert.assertNotNull("session should not be null",session);
  Properties properties=session.getProperties();
  Assert.assertNotNull("smtp host should be set",properties.getProperty("mail.smtp.host"));
  Assert.assertNotNull("pop3 host should be set",properties.getProperty("mail.pop3.host"));
  Assert.assertNotNull("imap host should be set",properties.getProperty("mail.imap.host"));
  PasswordAuthentication auth=session.requestPasswordAuthentication(InetAddress.getLocalHost(),25,"smtp","","");
  Assert.assertEquals("nobody",auth.getUserName());
  Assert.assertEquals("pass",auth.getPassword());
  ServiceController<?> defaultMailService=mainServices.getContainer().getService(MailSessionDefinition.SESSION_CAPABILITY.getCapabilityServiceName("default2"));
  session=(Session)defaultMailService.getValue();
  Assert.assertEquals("Debug should be true",true,session.getDebug());
  ServiceController<?> customMailService=mainServices.getContainer().getService(MailSessionDefinition.SESSION_CAPABILITY.getCapabilityServiceName("custom"));
  session=(Session)customMailService.getValue();
  properties=session.getProperties();
  String host=properties.getProperty("mail.smtp.host");
  Assert.assertNotNull("smtp host should be set",host);
  Assert.assertEquals("mail.example.com",host);
  Assert.assertEquals("localhost",properties.get("mail.pop3.host"));
  Assert.assertEquals("some-custom-prop-value",properties.get("mail.pop3.custom_prop"));
  Assert.assertEquals("fully-qualified-prop-name",properties.get("some.fully.qualified.property"));
  MailSessionService service=(MailSessionService)customMailService.getService();
  Credentials credentials=service.getConfig().getCustomServers()[0].getCredentials();
  Assert.assertEquals(credentials.getUsername(),"username");
  Assert.assertEquals(credentials.getPassword(),"password");
}
