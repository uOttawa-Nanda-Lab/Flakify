@Test public void testLoadBalanceWarmup(){
  Assert.assertEquals(1,calculateDefaultWarmupWeight(0));
  Assert.assertEquals(1,calculateDefaultWarmupWeight(13));
  Assert.assertEquals(1,calculateDefaultWarmupWeight(6 * 1000));
  Assert.assertEquals(2,calculateDefaultWarmupWeight(12 * 1000));
  Assert.assertEquals(10,calculateDefaultWarmupWeight(60 * 1000));
  Assert.assertEquals(50,calculateDefaultWarmupWeight(5 * 60 * 1000));
  Assert.assertEquals(50,calculateDefaultWarmupWeight(5 * 60 * 1000 + 23));
  Assert.assertEquals(50,calculateDefaultWarmupWeight(5 * 60 * 1000 + 5999));
  Assert.assertEquals(51,calculateDefaultWarmupWeight(5 * 60 * 1000 + 6000));
  Assert.assertEquals(90,calculateDefaultWarmupWeight(9 * 60 * 1000));
  Assert.assertEquals(98,calculateDefaultWarmupWeight(10 * 60 * 1000 - 12 * 1000));
  Assert.assertEquals(99,calculateDefaultWarmupWeight(10 * 60 * 1000 - 6 * 1000));
  Assert.assertEquals(100,calculateDefaultWarmupWeight(10 * 60 * 1000));
  Assert.assertEquals(100,calculateDefaultWarmupWeight(20 * 60 * 1000));
}
