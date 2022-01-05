@Test public void testRequestId() throws IOException, InvalidStateTransitionException, JsonGenerationException, JAXBException {
  HeartBeatHandler heartBeatHandler=injector.getInstance(HeartBeatHandler.class);
  Register register=new Register();
  register.setHostname("newHost");
  register.setTimestamp(new Date().getTime());
  register.setResponseId(123);
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("redhat5");
  register.setHardwareProfile(hi);
  RegistrationResponse registrationResponse=heartBeatHandler.handleRegistration(register);
  assertEquals("ResponseId should start from zero",0L,registrationResponse.getResponseId());
  HeartBeat heartBeat=constructHeartBeat("newHost",registrationResponse.getResponseId(),Status.HEALTHY);
  HeartBeatResponse hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);
  assertEquals("responseId was not incremented",1L,hbResponse.getResponseId());
  assertTrue("Not cached response returned",hbResponse == heartBeatHandler.handleHeartBeat(heartBeat));
  heartBeat.setResponseId(1L);
  hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);
  assertEquals("responseId was not incremented",2L,hbResponse.getResponseId());
  assertFalse("Agent is flagged for restart",hbResponse.isRestartAgent());
  log.debug(StageUtils.jaxbToString(hbResponse));
  heartBeat.setResponseId(20L);
  hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);
  assertTrue("Agent is not flagged for restart",hbResponse.isRestartAgent());
  log.debug(StageUtils.jaxbToString(hbResponse));
}
