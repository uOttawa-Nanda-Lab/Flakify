@Test public void testClientStateFlow() throws Exception {
  ServiceComponentHostImpl impl=(ServiceComponentHostImpl)createNewServiceComponentHost("HDFS","HDFS_CLIENT","h1",true);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_INSTALL,State.INIT,State.INSTALLING,State.INSTALL_FAILED,State.INSTALLED);
  boolean exceptionThrown=false;
  try {
    runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_START,State.INSTALLED,State.STARTING,State.START_FAILED,State.STARTED);
  }
 catch (  Exception e) {
    exceptionThrown=true;
  }
  Assert.assertTrue("Exception not thrown on invalid event",exceptionThrown);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_UNINSTALL,State.INSTALLED,State.UNINSTALLING,State.UNINSTALL_FAILED,State.UNINSTALLED);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_WIPEOUT,State.UNINSTALLED,State.WIPING_OUT,State.WIPEOUT_FAILED,State.INIT);
}
