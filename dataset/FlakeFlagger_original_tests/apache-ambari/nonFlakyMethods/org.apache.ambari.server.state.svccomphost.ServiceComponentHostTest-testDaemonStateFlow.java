@Test public void testDaemonStateFlow() throws Exception {
  ServiceComponentHostImpl impl=(ServiceComponentHostImpl)createNewServiceComponentHost("HDFS","DATANODE","h1",false);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_INSTALL,State.INIT,State.INSTALLING,State.INSTALL_FAILED,State.INSTALLED);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_START,State.INSTALLED,State.STARTING,State.START_FAILED,State.STARTED);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_STOP,State.STARTED,State.STOPPING,State.STOP_FAILED,State.INSTALLED);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_UNINSTALL,State.INSTALLED,State.UNINSTALLING,State.UNINSTALL_FAILED,State.UNINSTALLED);
  runStateChanges(impl,ServiceComponentHostEventType.HOST_SVCCOMP_WIPEOUT,State.UNINSTALLED,State.WIPING_OUT,State.WIPEOUT_FAILED,State.INIT);
}
