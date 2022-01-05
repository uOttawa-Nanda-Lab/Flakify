@Test public void testSuccessFactors(){
  Stage s=StageUtils.getATestStage(1,1);
  assertEquals(new Float(0.5),new Float(s.getSuccessFactor(Role.DATANODE)));
  assertEquals(new Float(0.5),new Float(s.getSuccessFactor(Role.TASKTRACKER)));
  assertEquals(new Float(0.5),new Float(s.getSuccessFactor(Role.GANGLIA_MONITOR)));
  assertEquals(new Float(0.5),new Float(s.getSuccessFactor(Role.HBASE_REGIONSERVER)));
  assertEquals(new Float(1.0),new Float(s.getSuccessFactor(Role.NAMENODE)));
  assertEquals(new Float(1.0),new Float(s.getSuccessFactor(Role.GANGLIA_SERVER)));
}
