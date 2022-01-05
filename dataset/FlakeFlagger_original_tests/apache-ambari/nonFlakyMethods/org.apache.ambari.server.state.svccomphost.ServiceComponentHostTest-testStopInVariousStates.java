@Test public void testStopInVariousStates() throws AmbariException, InvalidStateTransitionException {
  ServiceComponentHost sch=createNewServiceComponentHost("HDFS","DATANODE","h1",false);
  ServiceComponentHostImpl impl=(ServiceComponentHostImpl)sch;
  sch.setDesiredState(State.STARTED);
  sch.setState(State.START_FAILED);
  long timestamp=0;
  ServiceComponentHostEvent stopEvent=createEvent(impl,++timestamp,ServiceComponentHostEventType.HOST_SVCCOMP_STOP);
  long startTime=timestamp;
  impl.handleEvent(stopEvent);
  Assert.assertEquals(startTime,impl.getLastOpStartTime());
  Assert.assertEquals(-1,impl.getLastOpLastUpdateTime());
  Assert.assertEquals(-1,impl.getLastOpEndTime());
  Assert.assertEquals(State.STOPPING,impl.getState());
  sch.setState(State.INSTALL_FAILED);
  boolean exceptionThrown=false;
  try {
    impl.handleEvent(stopEvent);
  }
 catch (  Exception e) {
    exceptionThrown=true;
  }
  Assert.assertTrue("Exception not thrown on invalid event",exceptionThrown);
  Assert.assertEquals(startTime,impl.getLastOpStartTime());
  Assert.assertEquals(-1,impl.getLastOpLastUpdateTime());
  Assert.assertEquals(-1,impl.getLastOpEndTime());
  sch.setState(State.INSTALLED);
  ServiceComponentHostEvent stopEvent2=createEvent(impl,++timestamp,ServiceComponentHostEventType.HOST_SVCCOMP_STOP);
  startTime=timestamp;
  impl.handleEvent(stopEvent2);
  Assert.assertEquals(startTime,impl.getLastOpStartTime());
  Assert.assertEquals(-1,impl.getLastOpLastUpdateTime());
  Assert.assertEquals(-1,impl.getLastOpEndTime());
  Assert.assertEquals(State.STOPPING,impl.getState());
}
