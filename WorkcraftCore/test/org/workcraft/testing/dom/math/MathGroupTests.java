/*
*
* Copyright 2008,2009 Newcastle University
*
* This file is part of Workcraft.
*
* Workcraft is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Workcraft is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Workcraft.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package org.workcraft.testing.dom.math;

import org.junit.Test;
import org.workcraft.dom.math.MathGroup;
import org.workcraft.dom.math.MathNode;
import org.workcraft.observation.HierarchyEvent;
import org.workcraft.observation.HierarchyObserver;
import org.workcraft.observation.NodesAddedEvent;
import org.workcraft.observation.NodesDeletedEvent;

import static org.junit.Assert.*;

public class MathGroupTests {
    class MockNode extends MathNode {

    }

    private boolean receivedAddNotification1 = false;
    private boolean receivedAddNotification2 = false;
    private boolean receivedRemoveNotification1 = false;
    private boolean receivedRemoveNotification2 = false;

    private final MockNode n1 = new MockNode();
    private final MockNode n2 = new MockNode();

    @Test
    public void observationTest() {
        MathGroup group = new MathGroup();

        group.addObserver(new HierarchyObserver() {
            public void notify(HierarchyEvent e) {
                if (e instanceof NodesAddedEvent) {
                    if (e.getAffectedNodes().iterator().next() == n1) {
                        receivedAddNotification1 = true;
                    } else if (e.getAffectedNodes().iterator().next() == n2) {
                        receivedAddNotification2 = true;
                    }
                } else if (e instanceof NodesDeletedEvent) {
                    if (e.getAffectedNodes().iterator().next() == n1) {
                        receivedRemoveNotification1 = true;
                    } else if (e.getAffectedNodes().iterator().next() == n2) {
                        receivedRemoveNotification2 = true;
                    }
                }
            }
        });

        group.add(n1);
        assertTrue(receivedAddNotification1);
        group.add(n2);
        assertTrue(receivedAddNotification2);

        assertEquals(group.getChildren().size(), 2);

        group.remove(n2);
        assertTrue(receivedRemoveNotification2);
        group.remove(n1);
        assertTrue(receivedRemoveNotification1);

        assertEquals(group.getChildren().size(), 0);

        receivedAddNotification1 = false;
        receivedAddNotification2 = false;

        receivedRemoveNotification1 = false;
        receivedRemoveNotification2 = false;

        /*
         * groups no longer forward hierarchy event

        group2 = new MathGroup();
        group.add(group2);

        group2.add(n1);
        group2.add(n2);
        group2.remove(n2);
        group2.remove(n1);

        assertTrue(receivedAddNotification1);
        assertTrue(receivedAddNotification2);

        assertTrue(receivedRemoveNotification2);
        assertTrue(receivedRemoveNotification1); */
    }
}
