@Test public void testHostInfoImport() throws AmbariException {
  HostInfo info=new HostInfo();
  info.setMemorySize(100);
  info.setPhysicalProcessorCount(10);
  List<DiskInfo> mounts=new ArrayList<DiskInfo>();
  mounts.add(new DiskInfo("/dev/sda","/mnt/disk1","5000000","4000000","10%","size","fstype"));
  info.setMounts(mounts);
  info.setHostName("foo");
  info.setInterfaces("fip_4");
  info.setArchitecture("os_arch");
  info.setOS("os_type");
  info.setMemoryTotal(10);
  clusters.addHost("foo");
  Host host=clusters.getHost("foo");
  host.importHostInfo(info);
  Assert.assertEquals(info.getHostName(),host.getHostName());
  Assert.assertEquals(info.getFreeMemory(),host.getAvailableMemBytes());
  Assert.assertEquals(info.getMemoryTotal(),host.getTotalMemBytes());
  Assert.assertEquals(info.getPhysicalProcessorCount(),host.getCpuCount());
  Assert.assertEquals(info.getMounts().size(),host.getDisksInfo().size());
  Assert.assertEquals(info.getArchitecture(),host.getOsArch());
  Assert.assertEquals(info.getOS(),host.getOsType());
}
