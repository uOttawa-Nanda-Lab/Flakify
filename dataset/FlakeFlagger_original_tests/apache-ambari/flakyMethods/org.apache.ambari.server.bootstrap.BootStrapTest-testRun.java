@Test public void testRun() throws Exception {
  Properties properties=new Properties();
  String bootdir=temp.newFolder("bootdir").toString();
  LOG.info("Bootdir is " + bootdir);
  properties.setProperty(Configuration.BOOTSTRAP_DIR,bootdir);
  properties.setProperty(Configuration.BOOTSTRAP_SCRIPT,"echo");
  properties.setProperty(Configuration.SRVR_KSTR_DIR_KEY,"target" + File.separator + "classes");
  Configuration conf=new Configuration(properties);
  BootStrapImpl impl=new BootStrapImpl(conf);
  impl.init();
  SshHostInfo info=new SshHostInfo();
  info.setSshKey("xyz");
  ArrayList<String> hosts=new ArrayList<String>();
  hosts.add("host1");
  hosts.add("host2");
  info.setHosts(hosts);
  BSResponse response=impl.runBootStrap(info);
  LOG.info("Response id from bootstrap " + response.getRequestId());
  BootStrapStatus status=impl.getStatus(response.getRequestId());
  LOG.info("Status " + status.getStatus());
  int num=0;
  while ((status.getStatus() != BSStat.SUCCESS) && (num < 10000)) {
    status=impl.getStatus(response.getRequestId());
    Thread.sleep(100);
    num++;
  }
  LOG.info("Status: log " + status.getLog() + " status="+ status.getStatus());
  Assert.assertTrue(status.getLog().contains("host1,host2"));
  Assert.assertEquals(BSStat.SUCCESS,status.getStatus());
}
