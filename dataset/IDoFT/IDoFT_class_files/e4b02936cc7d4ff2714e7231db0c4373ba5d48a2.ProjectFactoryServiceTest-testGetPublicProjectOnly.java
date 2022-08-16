/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices
 * and license terms. Your use of these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.vmware.admiral.auth.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vmware.admiral.auth.AuthBaseTest;
import com.vmware.admiral.auth.idm.Principal;
import com.vmware.admiral.auth.idm.PrincipalRolesHandler.PrincipalRoleAssignment;
import com.vmware.admiral.auth.project.ProjectRolesHandler.ProjectRoles;
import com.vmware.admiral.auth.project.ProjectService.ExpandedProjectState;
import com.vmware.admiral.auth.project.ProjectService.ProjectState;
import com.vmware.admiral.compute.container.GroupResourcePlacementService;
import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.ServiceDocumentQueryResult;
import com.vmware.xenon.common.UriUtils;
import com.vmware.xenon.common.Utils;

public class ProjectFactoryServiceTest extends AuthBaseTest {

    private static final String PROJECT_NAME = "test-name";
    private static final String PROJECT_DESCRIPTION = "testDescription";
    private static final boolean PROJECT_IS_PUBLIC = false;

    private ProjectState project;

    @Before
    public void setUp() throws Throwable {
        waitForServiceAvailability(ProjectFactoryService.SELF_LINK);
        waitForServiceAvailability(GroupResourcePlacementService.FACTORY_LINK);

        host.assumeIdentity(buildUserServicePath(USER_EMAIL_ADMIN));
        project = createProject(PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_IS_PUBLIC);
        ProjectRoles projectRoles = new ProjectRoles();
        projectRoles.viewers = new PrincipalRoleAssignment();
        projectRoles.members = new PrincipalRoleAssignment();
        projectRoles.administrators = new PrincipalRoleAssignment();
        projectRoles.administrators.add = Collections.singletonList(USER_EMAIL_ADMIN);
        projectRoles.members.add = Collections.singletonList(USER_EMAIL_ADMIN);
        projectRoles.viewers.add = Collections.singletonList(USER_EMAIL_BASIC_USER);
        doPatch(projectRoles, project.documentSelfLink);
    }

    @Test
    public void testGetStateWithMembers() {
        URI uriWithExpand = UriUtils.extendUriWithQuery(
                UriUtils.buildUri(host, ProjectFactoryService.SELF_LINK),
                UriUtils.URI_PARAM_ODATA_EXPAND, Boolean.toString(true),
                UriUtils.URI_PARAM_ODATA_FILTER, String.format("%s eq '%s'",
                        ServiceDocument.FIELD_NAME_SELF_LINK, project.documentSelfLink));

        host.testStart(1);
        Operation.createGet(uriWithExpand)
                .setReferer(host.getUri())
                .setCompletion((o, e) -> {
                    if (e != null) {
                        host.failIteration(e);
                    } else {
                        ServiceDocumentQueryResult result = o.getBody(ServiceDocumentQueryResult.class);
                        try {
                            assertEquals(new Long(1), result.documentCount);

                            assertNotNull(result.documentLinks);
                            assertEquals(1, result.documentLinks.size());
                            assertEquals(project.documentSelfLink, result.documentLinks.iterator().next());

                            assertNotNull(result.documents);
                            assertEquals(1, result.documents.size());
                            assertEquals(project.documentSelfLink, result.documents.keySet().iterator().next());

                            Object jsonProject = result.documents.values().iterator().next();
                            ExpandedProjectState stateWithMembers = Utils.fromJson(jsonProject, ExpandedProjectState.class);
                            assertNotNull(stateWithMembers);
                            assertNotNull(stateWithMembers.administrators);
                            assertEquals(1, stateWithMembers.administrators.size());
                            assertEquals(USER_EMAIL_ADMIN,
                                    stateWithMembers.administrators.iterator().next().id);
                            assertNotNull(stateWithMembers.members);
                            assertEquals(1, stateWithMembers.members.size());
                            assertEquals(USER_EMAIL_ADMIN,
                                    stateWithMembers.members.iterator().next().id);
                            assertNotNull(stateWithMembers.viewers);
                            assertEquals(1, stateWithMembers.viewers.size());
                            assertEquals(USER_EMAIL_BASIC_USER,
                                    stateWithMembers.viewers.iterator().next().id);

                            host.completeIteration();
                        } catch (Throwable ex) {
                            host.failIteration(ex);
                        }
                    }
                }).sendWith(host);
        host.testWait();
    }

    @Test
    public void testGetPublicProjectOnly() throws Throwable {
        ProjectState testProject1 = createProject("test-project1", "test", true);
        ProjectState testProject2 = createProject("test-project2", "test", true);
        ProjectState testProject3 = createProject("test-project3", "test", false);
        ProjectState testProject4 = createProject("test-project4", "test", false);
        assertDocumentExists(testProject1.documentSelfLink);
        assertDocumentExists(testProject2.documentSelfLink);
        assertDocumentExists(testProject3.documentSelfLink);
        assertDocumentExists(testProject4.documentSelfLink);

        host.assumeIdentity(buildUserServicePath(USER_EMAIL_BASIC_USER));

        URI uri = UriUtils.buildUri(ProjectFactoryService.SELF_LINK);
        uri = UriUtils.extendUriWithQuery(uri, ProjectFactoryService.QUERY_PARAM_PUBLIC, "");

        ServiceDocumentQueryResult getResult = getDocumentNoWait(ServiceDocumentQueryResult.class,
                uri.toString());

        assertEquals(Long.valueOf(2), getResult.documentCount);
        assertEquals(2, getResult.documentLinks.size());
        assertEquals(2, getResult.documents.size());
        assertTrue(getResult.documentLinks.contains(testProject1.documentSelfLink));
        assertTrue(getResult.documentLinks.contains(testProject2.documentSelfLink));
        assertTrue(!getResult.documentLinks.contains(testProject3.documentSelfLink));
        assertTrue(!getResult.documentLinks.contains(testProject4.documentSelfLink));
    }

    @Test
    public void testGetBasicExpand() throws Throwable {
        URI uri = UriUtils.buildUri(ProjectFactoryService.SELF_LINK);
        uri = UriUtils.extendUriWithQuery(uri, UriUtils.URI_PARAM_ODATA_EXPAND_NO_DOLLAR_SIGN,
                Boolean.TRUE.toString());

        ServiceDocumentQueryResult result = getDocumentNoWait(ServiceDocumentQueryResult.class,
                uri.toString());

        assertEquals(2, result.documentLinks.size());
        assertEquals(2, result.documents.size());

        ExpandedProjectState expandedProjectState = null;

        for (Object obj : result.documents.values()) {
            ExpandedProjectState state = Utils.fromJson(obj, ExpandedProjectState.class);
            if (state.name.equalsIgnoreCase(project.name)) {
                expandedProjectState = state;
                break;
            }
        }

        assertNotNull(expandedProjectState);
        assertEquals(project.name, expandedProjectState.name);
        assertEquals(project.isPublic, expandedProjectState.isPublic);
        assertNotNull(expandedProjectState.members);
        assertNotNull(expandedProjectState.viewers);
        assertNotNull(expandedProjectState.administrators);

        assertEquals(1, expandedProjectState.members.size());
        assertEquals(1, expandedProjectState.viewers.size());
        assertEquals(1, expandedProjectState.administrators.size());

        List<Principal> principals = Arrays.asList(expandedProjectState.members.iterator().next(),
                expandedProjectState.administrators.iterator().next(),
                expandedProjectState.viewers.iterator().next());

        for (Principal p : principals) {
            assertNotNull(p.id);
            assertNull(p.email);
            assertNull(p.type);
            assertNull(p.name);
            assertNull(p.source);
        }
    }

    // VBV-2177
    @Test
    public void testGetStateWithFilter() {
        URI uriWithFilter = UriUtils.extendUriWithQuery(
                UriUtils.buildUri(host, ProjectFactoryService.SELF_LINK),
                UriUtils.URI_PARAM_ODATA_FILTER, String.format("%s eq '%s'",
                        ServiceDocument.FIELD_NAME_SELF_LINK, project.documentSelfLink));

        host.testStart(1);
        Operation.createGet(uriWithFilter)
                .setReferer(host.getUri())
                .setCompletion((o, e) -> {
                    if (e != null) {
                        host.failIteration(e);
                    } else {
                        ServiceDocumentQueryResult result = o
                                .getBody(ServiceDocumentQueryResult.class);
                        try {
                            assertEquals(new Long(1), result.documentCount);

                            assertNotNull(result.documentLinks);
                            assertEquals(1, result.documentLinks.size());
                            assertEquals(project.documentSelfLink,
                                    result.documentLinks.iterator().next());

                            assertNotNull(result.documents);
                            host.completeIteration();
                        } catch (Throwable ex) {
                            host.failIteration(ex);
                        }
                    }
                }).sendWith(host);
        host.testWait();
    }

    @Test
    public void testDeleteFactoryShouldFail() throws Throwable {
        try {
            doDelete(UriUtils.buildUri(host, ProjectFactoryService.SELF_LINK), false);
            fail(EXPECTED_ILLEGAL_ACCESS_ERROR_MESSAGE);
        } catch (IllegalAccessError e) {
            assertTrue(e.getMessage().toLowerCase().startsWith(FORBIDDEN));
        }
    }
}
