@Test public void testSuccessCriteria(){
  RoleStats rs1=new RoleStats(1,(float)0.5);
  rs1.numSucceeded=1;
  assertTrue(rs1.isSuccessFactorMet());
  rs1.numSucceeded=0;
  assertFalse(rs1.isSuccessFactorMet());
  RoleStats rs2=new RoleStats(2,(float)0.5);
  rs2.numSucceeded=1;
  assertTrue(rs2.isSuccessFactorMet());
  RoleStats rs3=new RoleStats(3,(float)0.5);
  rs3.numSucceeded=2;
  assertTrue(rs2.isSuccessFactorMet());
  rs3.numSucceeded=1;
  assertFalse(rs3.isSuccessFactorMet());
  RoleStats rs4=new RoleStats(3,(float)1.0);
  rs4.numSucceeded=2;
  assertFalse(rs3.isSuccessFactorMet());
}
