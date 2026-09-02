// incidentApi.js
// REST API Client for Smart Incident Management & Analytics Backend

const BASE_URL = '/api/v1';

async function fetchWithAuth(url, options = {}) {
  const token = localStorage.getItem('token');
  const headers = {
    'Content-Type': 'application/json',
    ...(token ? { 'Authorization': `Bearer ${token}` } : {}),
    ...options.headers,
  };

  const response = await fetch(`${BASE_URL}${url}`, {
    ...options,
    headers,
  });

  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}));
    throw new Error(errorData.message || `HTTP Error ${response.status}`);
  }

  return response.json();
}

export const incidentApi = {
  // Authentication
  login: async (email, password) => {
    return fetchWithAuth('/auth/login', {
      method: 'POST',
      body: JSON.stringify({ email, password }),
    });
  },

  // Incident Operations
  getIncidents: async (filters = {}) => {
    const queryParams = new URLSearchParams(filters).toString();
    const url = `/incidents${queryParams ? `?${queryParams}` : ''}`;
    return fetchWithAuth(url);
  },

  getIncidentById: async (id) => {
    return fetchWithAuth(`/incidents/${id}`);
  },

  createIncident: async (incidentData) => {
    return fetchWithAuth('/incidents', {
      method: 'POST',
      body: JSON.stringify(incidentData),
    });
  },

  assignAgent: async (id, agentId) => {
    return fetchWithAuth(`/incidents/${id}/assign`, {
      method: 'PUT',
      body: JSON.stringify({ agentId }),
    });
  },

  updateStatus: async (id, status) => {
    return fetchWithAuth(`/incidents/${id}/status`, {
      method: 'PUT',
      body: JSON.stringify({ status }),
    });
  },

  resolveIncident: async (id, resolutionData) => {
    return fetchWithAuth(`/incidents/${id}/resolve`, {
      method: 'POST',
      body: JSON.stringify(resolutionData),
    });
  },

  addComment: async (id, commentData) => {
    return fetchWithAuth(`/incidents/${id}/comments`, {
      method: 'POST',
      body: JSON.stringify(commentData),
    });
  },

  // Management & Analytics
  getAnalyticsMetrics: async () => {
    return fetchWithAuth('/analytics/metrics');
  },

  // Knowledge Base
  getKnowledgeBaseArticles: async () => {
    return fetchWithAuth('/kb');
  },
};
