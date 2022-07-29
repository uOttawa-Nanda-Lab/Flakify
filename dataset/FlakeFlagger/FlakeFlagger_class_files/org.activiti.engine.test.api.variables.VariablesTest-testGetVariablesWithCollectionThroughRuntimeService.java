package org.activiti.engine.test.api.variables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Testing various constructs with variables. Created to test the changes done in https://jira.codehaus.org/browse/ACT-1900.
 * 

 */
public class VariablesTest extends PluggableActivitiTestCase {

  protected String processInstanceId;

  private Map<String, Object> generateVariables() {
    Map<String, Object> vars = new HashMap<String, Object>();

    // 10 Strings
    for (int i = 0; i < 10; i++) {
      vars.put("stringVar" + i, "stringVarValue-" + i);
    }

    // 10 integers
    for (int i = 0; i < 10; i++) {
      vars.put("intVar" + i, i * 100);
    }

    // 10 dates
    for (int i = 0; i < 10; i++) {
      vars.put("dateVar" + i, new Date());
    }
    
    // 10 joda local dates
    for (int i = 0; i < 10; i++) {
      vars.put("localdateVar" + i, new LocalDate());
    }
    
    // 10 joda local dates
    for (int i = 0; i < 10; i++) {
      vars.put("datetimeVar" + i, new DateTime());
    }

    // 10 booleans
    for (int i = 0; i < 10; i++) {
      vars.put("booleanValue" + i, (i % 2 == 0));
    }

    // 10 Serializables
    for (int i = 0; i < 10; i++) {
      vars.put("serializableValue" + i, new TestSerializableVariable(i));
    }
    return vars;
  }

  public void testGetVariablesWithCollectionThroughRuntimeService(){Map<String, Object> vars=runtimeService.getVariables(processInstanceId,Arrays.asList("intVar1","intVar3","intVar5","intVar9"));assertEquals(4,vars.size());assertEquals(100,vars.get("intVar1"));assertEquals(300,vars.get("intVar3"));assertEquals(500,vars.get("intVar5"));assertEquals(900,vars.get("intVar9"));assertEquals(4,runtimeService.getVariablesLocal(processInstanceId,Arrays.asList("intVar1","intVar3","intVar5","intVar9")).size());Task task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().taskName("Task 3").singleResult();String executionId=task.getExecutionId();assertFalse(processInstanceId.equals(executionId));assertEquals(0,runtimeService.getVariablesLocal(executionId,Arrays.asList("intVar1","intVar3","intVar5","intVar9")).size());}

  // Class to test variable serialization
  public static class TestSerializableVariable implements Serializable {

    private static final long serialVersionUID = 1L;
    private int number;

    public TestSerializableVariable(int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }

    public void setNumber(int number) {
      this.number = number;
    }

  }

  // Test delegates
  public static class TestJavaDelegate1 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar");
      execution.setVariable("testVar", var.toUpperCase());
    }
  }

  public static class TestJavaDelegate2 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar");
      execution.setVariable("testVar", var + " world");
    }
  }

  public static class TestJavaDelegate3 implements JavaDelegate {
    public void execute(DelegateExecution execution) {

    }
  }

  // ////////////////////////////////////////

  public static class TestJavaDelegate4 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", var.toUpperCase());
    }
  }

  public static class TestJavaDelegate5 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", var + " world");
    }
  }

  public static class TestJavaDelegate6 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", var + "!");
    }
  }

  // ////////////////////////////////////////

  public static class TestJavaDelegate7 implements JavaDelegate {
    public void execute(DelegateExecution execution) {

      // Setting variable through 'default' way of setting variable
      execution.setVariable("testVar", "test");

    }
  }

  public static class TestJavaDelegate8 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String var = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", var + " 1 2 3");
    }
  }

  public static class TestJavaDelegate9 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      execution.setVariable("testVar2", "Hiya");
    }
  }

  // ////////////////////////////////////////

  public static class TestJavaDelegate10 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String testVar = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", testVar + "2");
    }
  }

  public static class TestJavaDelegate11 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String testVar = (String) execution.getVariable("testVar", false);
      execution.setVariable("testVar", testVar + "3");
    }
  }

  public static class TestJavaDelegate12 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String testVar = (String) execution.getVariable("testVar");
      execution.setVariable("testVar", testVar + "4");
    }
  }

  // ////////////////////////////////////////

  public static class TestJavaDelegate13 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      Map<String, Object> vars = execution.getVariables(Arrays.asList("testVar1", "testVar2", "testVar3"), false);

      String testVar1 = (String) vars.get("testVar1");
      String testVar2 = (String) vars.get("testVar2");
      String testVar3 = (String) vars.get("testVar3");

      execution.setVariable("testVar1", testVar1 + "-CHANGED", false);
      execution.setVariable("testVar2", testVar2 + "-CHANGED", false);
      execution.setVariable("testVar3", testVar3 + "-CHANGED", false);

      execution.setVariableLocal("localVar", "localValue", false);
    }
  }

  public static class TestJavaDelegate14 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String value = (String) execution.getVariable("testVar2");
      String localVarValue = (String) execution.getVariableLocal("localValue");
      execution.setVariableLocal("testVar2", value + localVarValue);
    }
  }

  public static class TestJavaDelegate15 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      execution.removeVariable("testVar3");
    }
  }

}
