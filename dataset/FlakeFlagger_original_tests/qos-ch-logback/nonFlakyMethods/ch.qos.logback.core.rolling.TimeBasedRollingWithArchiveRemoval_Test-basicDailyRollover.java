@Test public void basicDailyRollover(){
  int maxHistory=20;
  int simulatedNumberOfPeriods=20 * 3;
  int startInactivity=0;
  int numInactivityPeriods=0;
  generateDailyRollover(currentTime,maxHistory,simulatedNumberOfPeriods,startInactivity,numInactivityPeriods);
}
