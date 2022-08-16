/*
 * The MIT License
 *
 * Copyright (c) 2018, Ilia Zasimov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jenkins.cli;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

import hudson.Functions;
import hudson.cli.CLICommand;
import hudson.cli.CLICommandInvoker;
import hudson.model.FreeStyleProject;
import hudson.model.Item;
import hudson.tasks.BatchFile;
import hudson.tasks.Builder;
import hudson.tasks.Shell;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import jenkins.model.Jenkins;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.MockAuthorizationStrategy;

public class StopBuildsCommandTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    private static final String TEST_JOB_NAME = "jobName";
    private static final String TEST_JOB_NAME_2 = "jobName2";
    private static final String LN = System.lineSeparator();

    @Test
    public void shouldStopLastBuild() throws Exception {
        final FreeStyleProject project = createLongRunningProject(TEST_JOB_NAME);
        project.scheduleBuild2(0).waitForStart();
        final String stdout = runWith(Collections.singletonList(TEST_JOB_NAME)).stdout();

        assertThat(stdout, equalTo("Build '#1' stopped for job 'jobName'" + LN));

        waitForLastBuildToStop(project);
    }

    @Test
    public void shouldNotStopEndedBuild() throws Exception {
        final FreeStyleProject project = j.createFreeStyleProject(TEST_JOB_NAME);
        project.getBuildersList().add(createScriptBuilder("echo 1"));
        project.scheduleBuild2(0).waitForStart();
        waitForLastBuildToStop(project);

        final String out = runWith(Collections.singletonList(TEST_JOB_NAME)).stdout();

        assertThat(out, equalTo("No builds stopped" + LN));
    }

    @Test
    public void shouldStopSeveralWorkingBuilds() throws Exception {
        final FreeStyleProject project = createLongRunningProject(TEST_JOB_NAME);
        project.setConcurrentBuild(true);

        project.scheduleBuild2(0).waitForStart();
        project.scheduleBuild2(0).waitForStart();

        final String stdout = runWith(Collections.singletonList(TEST_JOB_NAME)).stdout();

        assertThat(stdout, equalTo("Build '#2' stopped for job 'jobName'" + LN +
                "Build '#1' stopped for job 'jobName'" + LN));
        waitForLastBuildToStop(project);
    }

    @Test
    public void shouldReportNotSupportedType() throws Exception {
        final String testFolderName = "folder";
        j.createFolder(testFolderName);

        final String stderr = runWith(Collections.singletonList(testFolderName)).stderr();

        assertThat(stderr, equalTo(LN + "ERROR: Job not found: 'folder'" + LN));
    }

    @Test
    public void shouldDoNothingIfJobNotFound() throws Exception {
        final String stderr = runWith(Collections.singletonList(TEST_JOB_NAME)).stderr();

        assertThat(stderr, equalTo(LN + "ERROR: Job not found: 'jobName'" + LN));
    }

    @Test
    public void shouldStopWorkingBuildsInSeveralJobs() throws Exception {
        final List<String> inputJobNames = asList(TEST_JOB_NAME, TEST_JOB_NAME_2);
        setupAndAssertTwoBuildsStop(inputJobNames);
    }

    @Test
    public void shouldFilterJobDuplicatesInInput() throws Exception {
        final List<String> inputNames = asList(TEST_JOB_NAME, TEST_JOB_NAME, TEST_JOB_NAME_2);
        setupAndAssertTwoBuildsStop(inputNames);
    }

    @Test
    public void shouldReportBuildStopError() throws Exception {
        final FreeStyleProject project = createLongRunningProject(TEST_JOB_NAME);

        j.jenkins.setSecurityRealm(j.createDummySecurityRealm());
        j.jenkins.setAuthorizationStrategy(new MockAuthorizationStrategy().
                grant(Jenkins.READ).everywhere().toEveryone().
                grant(Item.READ).onItems(project).toEveryone().
                grant(Item.CANCEL).onItems(project).toAuthenticated());
        project.scheduleBuild2(0).waitForStart();

        final String stdout = runWith(Collections.singletonList(TEST_JOB_NAME)).stdout();

        assertThat(stdout,
                equalTo("Exception occurred while trying to stop build '#1' for job 'jobName'. " +
                        "Exception class: AccessDeniedException3, message: anonymous is missing the Job/Cancel permission" + LN +
                        "No builds stopped" + LN));
    }

    @Test
    public void shouldStopSecondJobEvenIfFirstStopFailed() throws Exception {
        final FreeStyleProject project = createLongRunningProject(TEST_JOB_NAME_2);

        final FreeStyleProject restrictedProject = createLongRunningProject(TEST_JOB_NAME);

        j.jenkins.setSecurityRealm(j.createDummySecurityRealm());
        j.jenkins.setAuthorizationStrategy(new MockAuthorizationStrategy().
                grant(Jenkins.READ).everywhere().toEveryone().
                grant(Item.READ).onItems(restrictedProject, project).toEveryone().
                grant(Item.CANCEL).onItems(restrictedProject).toAuthenticated().
                grant(Item.CANCEL).onItems(project).toEveryone());

        restrictedProject.scheduleBuild2(0).waitForStart();
        project.scheduleBuild2(0).waitForStart();

        final String stdout = runWith(asList(TEST_JOB_NAME, TEST_JOB_NAME_2)).stdout();
        List<String> testList = Arrays.asList(stdout.split(LN));
        assertThat(testList,
                contains("Exception occurred while trying to stop build '#1' for job 'jobName'. " +
                        "Exception class: AccessDeniedException3, message: anonymous is missing the Job/Cancel permission",
                        "Build '#1' stopped for job 'jobName2'"));
    }

    private CLICommandInvoker.Result runWith(final List<String> jobNames) throws Exception {
        CLICommand cmd = new StopBuildsCommand();
        CLICommandInvoker invoker = new CLICommandInvoker(j, cmd);
        return invoker.invokeWithArgs(jobNames.toArray(new String[jobNames.size()]));
    }

    private void setupAndAssertTwoBuildsStop(final List<String> inputNames) throws Exception {
        final FreeStyleProject project = createLongRunningProject(TEST_JOB_NAME);
        final FreeStyleProject project2 = createLongRunningProject(TEST_JOB_NAME_2);

        project.scheduleBuild2(0).waitForStart();
        project2.scheduleBuild2(0).waitForStart();

        final String stdout = runWith(inputNames).stdout();
        List<String> testList = Arrays.asList(stdout.split(LN));
        assertThat(testList,
                contains("Build '#1' stopped for job 'jobName'",
                        "Build '#1' stopped for job 'jobName2'"));

        waitForLastBuildToStop(project);
        waitForLastBuildToStop(project2);
    }

    private FreeStyleProject createLongRunningProject(final String jobName) throws IOException {
        final FreeStyleProject project = j.createFreeStyleProject(jobName);
        project.getBuildersList().add(createScriptBuilder("sleep 50000"));
        return project;
    }

    private Builder createScriptBuilder(String script) {
        return Functions.isWindows() ? new BatchFile(script) : new Shell(script);
    }

    private void waitForLastBuildToStop(final FreeStyleProject project) throws InterruptedException {
        while (project.getLastBuild().isBuilding()) {
            Thread.sleep(500);
        }
        assertThat(project.getLastBuild().isBuilding(), equalTo(false));
    }
}
