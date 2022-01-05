/** 
 * Even if outgoing RunAs from previous state is empty, incoming RunAs has to be used for incomingRunAs for current state.
 * @param incomingRunAs Incoming RunAs for previous state.
 * @param outgoingRunAs Outgoung RunAs for previous state.
 */
private void testHandlingRunAs(RunAs incomingRunAs,RunAs outgoingRunAs){
  SecurityContext context=mock(SecurityContext.class);
  if (outgoingRunAs != null) {
    when(context.getOutgoingRunAs()).thenReturn(outgoingRunAs);
  }
  if (incomingRunAs != null) {
    when(context.getIncomingRunAs()).thenReturn(incomingRunAs);
  }
  SecurityContextAssociation.setSecurityContext(context);
  simpleSecurityManager.push("test");
  SecurityContext result=SecurityContextAssociation.getSecurityContext();
  if (outgoingRunAs != null) {
    Assert.assertEquals("RunAs identity has to be same as previous outgoing RunAs.",outgoingRunAs,result.getIncomingRunAs());
  }
 else   if (incomingRunAs != null) {
    Assert.assertEquals("RunAs identity has to be same as previous incoming RunAs.",incomingRunAs,result.getIncomingRunAs());
  }
 else {
    Assert.assertNull("RunAs identity has to be null.",result.getIncomingRunAs());
  }
}
