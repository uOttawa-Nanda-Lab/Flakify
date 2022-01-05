@Test public void smoke(){
  int EXPECTED_AVERAGE=6;
  int EXPECTED_STD_DEVIATION=3;
  System.out.println();
  Random r=new Random(now);
  int len=3000;
  int[] valArray=new int[len];
  for (int i=0; i < len; i++) {
    valArray[i]=RandomUtil.gaussianAsPositiveInt(r,EXPECTED_AVERAGE,EXPECTED_STD_DEVIATION);
  }
  double avg=average(valArray);
  assertEquals(EXPECTED_AVERAGE,avg,0.3);
}
