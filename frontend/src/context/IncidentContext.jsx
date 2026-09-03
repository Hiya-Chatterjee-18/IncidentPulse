import React, { createContext, useContext, useState, useEffect } from 'react';
import { initialIncidents, initialUsers, initialKBArticles } from '../data/seedData';
import { incidentApi } from '../api/incidentApi';

const IncidentContext = createContext();

export function IncidentProvider({ children }) {
  const [incidents, setIncidents] = useState(initialIncidents);
  const [users, setUsers] = useState(initialUsers);
  const [kbArticles, setKbArticles] = useState(initialKBArticles);
  const [selectedIncident, setSelectedIncident] = useState(null);
  const [currentRole, setCurrentRole] = useState('REPORTER'); // 'REPORTER', 'AGENT', 'ADMIN'
  const [currentUser, setCurrentUser] = useState(initialUsers[0]);

  // Load real API data when available
  useEffect(() => {
    async function loadData() {
      try {
        const apiIncidents = await incidentApi.getIncidents();
        if (apiIncidents && apiIncidents.length > 0) {
          setIncidents(apiIncidents);
        }
      } catch (e) {
        // Fallback to initial local state if backend API is not running
      }
    }
    loadData();
  }, []);

  const createIncident = async (newIncidentData) => {
    const id = `INC-${Math.floor(10000 + Math.random() * 90000)}`;
    const newIncident = {
      id,
      ...newIncidentData,
      status: 'OPEN',
      reporterId: currentUser.id,
      reporterName: currentUser.name,
      createdAt: new Date().toISOString(),
      comments: [],
      history: [{ actor: currentUser.name, action: 'Created Incident', timestamp: new Date().toISOString() }],
    };

    setIncidents(prev => [newIncident, ...prev]);

    try {
      await incidentApi.createIncident(newIncidentData);
    } catch (e) {
      // Handled via local fallback
    }
  };

  const updateStatus = async (id, newStatus) => {
    setIncidents(prev => prev.map(inc => {
      if (inc.id === id) {
        return {
          ...inc,
          status: newStatus,
          history: [...inc.history, { actor: currentUser.name, action: `Changed Status to ${newStatus}`, timestamp: new Date().toISOString() }]
        };
      }
      return inc;
    }));

    try {
      await incidentApi.updateStatus(id, newStatus);
    } catch (e) {}
  };

  const assignAgent = async (id, agentId) => {
    const agent = users.find(u => u.id === agentId);
    setIncidents(prev => prev.map(inc => {
      if (inc.id === id) {
        return {
          ...inc,
          assigneeId: agentId,
          assigneeName: agent ? agent.name : 'Agent',
          status: inc.status === 'OPEN' ? 'ASSIGNED' : inc.status,
          history: [...inc.history, { actor: currentUser.name, action: `Assigned to ${agent ? agent.name : 'Agent'}`, timestamp: new Date().toISOString() }]
        };
      }
      return inc;
    }));

    try {
      await incidentApi.assignAgent(id, agentId);
    } catch (e) {}
  };

  return (
    <IncidentContext.Provider
      value={{
        incidents,
        users,
        kbArticles,
        selectedIncident,
        setSelectedIncident,
        currentRole,
        setCurrentRole,
        currentUser,
        setCurrentUser,
        createIncident,
        updateStatus,
        assignAgent,
      }}
    >
      {children}
    </IncidentContext.Provider>
  );
}

export function useIncidentContext() {
  return useContext(IncidentContext);
}
