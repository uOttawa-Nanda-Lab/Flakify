@Test public void testEquals(){
  URL statistics=new URL("dubbo","10.20.153.10",0).addParameter(MonitorService.APPLICATION,"morgan").addParameter(MonitorService.INTERFACE,"MemberService").addParameter(MonitorService.METHOD,"findPerson").addParameter(MonitorService.CONSUMER,"10.20.153.11").addParameter(MonitorService.SUCCESS,1).addParameter(MonitorService.FAILURE,0).addParameter(MonitorService.ELAPSED,3).addParameter(MonitorService.MAX_ELAPSED,3).addParameter(MonitorService.CONCURRENT,1).addParameter(MonitorService.MAX_CONCURRENT,1);
  Statistics statistics1=new Statistics(statistics);
  Statistics statistics2=new Statistics(statistics);
  Assert.assertThat(statistics1,equalTo(statistics1));
  Assert.assertThat(statistics1,equalTo(statistics2));
  statistics1.setVersion("2");
  Assert.assertThat(statistics1,not(equalTo(statistics2)));
  Assert.assertThat(statistics1.hashCode(),not(equalTo(statistics2.hashCode())));
  statistics1.setMethod("anotherMethod");
  Assert.assertThat(statistics1,not(equalTo(statistics2)));
  Assert.assertThat(statistics1.hashCode(),not(equalTo(statistics2.hashCode())));
  statistics1.setClient("anotherClient");
  Assert.assertThat(statistics1,not(equalTo(statistics2)));
  Assert.assertThat(statistics1.hashCode(),not(equalTo(statistics2.hashCode())));
}
