@Test public void testEventPublisherInjected() throws Exception {
  testAuthenticationManagerCreated();
  final AtomicReference<ApplicationEvent> wrapper=new AtomicReference<ApplicationEvent>();
  this.context.addApplicationListener(new ApplicationListener<ApplicationEvent>(){
    @Override public void onApplicationEvent(    ApplicationEvent event){
      wrapper.set(event);
    }
  }
);
  AuthenticationManager manager=this.context.getBean(AuthenticationManager.class);
  try {
    manager.authenticate(new UsernamePasswordAuthenticationToken("foo","bar"));
    fail("Expected BadCredentialsException");
  }
 catch (  BadCredentialsException e) {
  }
  assertTrue(wrapper.get() instanceof AuthenticationFailureBadCredentialsEvent);
}
