@Test public void dailyRolloverWithInactivity10(){
  int maxHistory=6;
  int simulatedNumberOfPeriods=10;
  int startInactivity=3;
  int numInactivityPeriods=4;
  generateDailyRollover(currentTime,maxHistory,simulatedNumberOfPeriods,startInactivity,numInactivityPeriods);
}
