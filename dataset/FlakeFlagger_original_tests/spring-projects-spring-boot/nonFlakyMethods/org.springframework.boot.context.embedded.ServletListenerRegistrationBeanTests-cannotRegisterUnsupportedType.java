@Test public void cannotRegisterUnsupportedType() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Listener is not of a supported type");
  new ServletListenerRegistrationBean<EventListener>(new EventListener(){
  }
);
}
