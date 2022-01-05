@Test public void testGetHosts() throws AmbariException {
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  clusters.getCluster("c1").setDesiredStackVersion(new StackId("HDP-0.2"));
  clusters.getCluster("c2").setDesiredStackVersion(new StackId("HDP-0.2"));
  clusters.addHost("h1");
  clusters.addHost("h2");
  clusters.addHost("h3");
  clusters.addHost("h4");
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost("h3").setOsType("centos5");
  clusters.getHost("h4").setOsType("centos5");
  clusters.getHost("h1").persist();
  clusters.getHost("h2").persist();
  clusters.getHost("h3").persist();
  clusters.getHost("h4").persist();
  clusters.mapHostToCluster("h1","c1");
  clusters.mapHostToCluster("h1","c2");
  clusters.mapHostToCluster("h2","c1");
  clusters.mapHostToCluster("h3","c1");
  Map<String,String> attrs=new HashMap<String,String>();
  attrs.put("a1","b1");
  clusters.getHost("h3").setHostAttributes(attrs);
  attrs.put("a2","b2");
  clusters.getHost("h4").setHostAttributes(attrs);
  HostRequest r=new HostRequest(null,null,null);
  Set<HostResponse> resps=controller.getHosts(Collections.singleton(r));
  Assert.assertEquals(4,resps.size());
  Set<String> foundHosts=new HashSet<String>();
  for (  HostResponse resp : resps) {
    foundHosts.add(resp.getHostname());
    if (resp.getHostname().equals("h1")) {
      Assert.assertEquals(2,resp.getClusterNames().size());
      Assert.assertEquals(0,resp.getHostAttributes().size());
    }
 else     if (resp.getHostname().equals("h2")) {
      Assert.assertEquals(1,resp.getClusterNames().size());
      Assert.assertEquals(0,resp.getHostAttributes().size());
    }
 else     if (resp.getHostname().equals("h3")) {
      Assert.assertEquals(1,resp.getClusterNames().size());
      Assert.assertEquals(1,resp.getHostAttributes().size());
    }
 else     if (resp.getHostname().equals("h4")) {
      Assert.assertEquals(0,resp.getClusterNames().size());
      Assert.assertEquals(2,resp.getHostAttributes().size());
    }
 else {
      fail("Found invalid host");
    }
  }
  Assert.assertEquals(4,foundHosts.size());
  r=new HostRequest("h1",null,null);
  resps=controller.getHosts(Collections.singleton(r));
  Assert.assertEquals(1,resps.size());
  HostResponse resp=resps.iterator().next();
  Assert.assertEquals("h1",resp.getHostname());
  Assert.assertEquals(2,resp.getClusterNames().size());
  Assert.assertEquals(0,resp.getHostAttributes().size());
}
