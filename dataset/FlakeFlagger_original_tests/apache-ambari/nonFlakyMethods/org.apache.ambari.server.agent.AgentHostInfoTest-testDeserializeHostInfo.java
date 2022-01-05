@Test public void testDeserializeHostInfo() throws JsonParseException, JsonMappingException, IOException {
  String hostinfo="{\"architecture\": \"x86_64\", " + "\"augeasversion\": \"0.10.0\"," + "\"domain\": \"test.com\", "+ "\"facterversion\": \"1.6.10\","+ "\"fqdn\": \"dev.test.com\", "+ "\"hardwareisa\": \"x86_64\", "+ "\"hardwaremodel\": \"x86_64\","+ "\"hostname\": \"dev\", "+ "\"id\": \"root\", "+ "\"interfaces\": \"eth0,lo\", "+ "\"ipaddress\": \"10.0.2.15\","+ "\"ipaddress_eth0\": \"10.0.2.15\","+ "\"ipaddress_lo\": \"127.0.0.1\","+ "\"is_virtual\": true,"+ "\"kernel\": \"Linux\", "+ "\"kernelmajversion\": \"2.6\","+ "\"kernelrelease\": \"2.6.18-238.12.1.el5\","+ "\"kernelversion\": \"2.6.18\", "+ "\"lsbdistcodename\": \"Final\","+ "\"lsbdistdescription\": \"CentOS release 5.8 (Final)\","+ "\"lsbdistid\": \"CentOS\", "+ "\"lsbdistrelease\": \"5.8\", "+ "\"lsbmajdistrelease\": \"5\","+ "\"macaddress\": \"08:00:27:D2:59:B2\", "+ "\"macaddress_eth0\": \"08:00:27:D2:59:B2\","+ "\"manufacturer\": \"innotek GmbH\","+ "\"memoryfree\": 2453667,"+ "\"memorysize\": 3051356, "+ "\"memorytotal\": 3051356,"+ "\"netmask\": \"255.255.255.0\"}";
  ObjectMapper mapper=new ObjectMapper();
  mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
  HostInfo info=mapper.readValue(hostinfo,HostInfo.class);
  Assert.assertEquals(info.getMemoryTotal(),3051356L);
  Assert.assertEquals(info.getKernel(),"Linux");
  Assert.assertEquals(info.getFQDN(),"dev.test.com");
  Assert.assertEquals(info.getAgentUserId(),"root");
  Assert.assertEquals(info.getMemorySize(),3051356L);
  Assert.assertEquals(info.getArchitecture(),"x86_64");
}
