// seedData.js
// Mock Seed Data for Frontend Demo & Offline Fallback

export const initialUsers = [
  {
    id: 'u-user1',
    name: 'Sarah Jenkins',
    email: 'sarah.j@company.com',
    role: 'REPORTER',
    department: 'Finance',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Sarah'
  },
  {
    id: 'u-agent1',
    name: 'David Chen',
    email: 'david.chen@company.com',
    role: 'AGENT',
    department: 'Infrastructure',
    specialty: 'Database & Backend',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=David'
  },
  {
    id: 'u-admin',
    name: 'Alex Morgan',
    email: 'alex.morgan@company.com',
    role: 'ADMIN',
    department: 'IT Management',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Alex'
  }
];

export const initialIncidents = [
  {
    id: 'INC-89210',
    title: 'Production Payment Gateway 500 Outage',
    category: 'Payment Gateway',
    description: 'Payment API returning 500 internal server error for checkout transactions during peak hours.',
    priority: 'CRITICAL',
    status: 'IN_PROGRESS',
    impact: 'Organization',
    urgency: 'Critical',
    reporterId: 'u-user1',
    reporterName: 'Sarah Jenkins',
    assigneeId: 'u-agent1',
    assigneeName: 'David Chen',
    createdAt: new Date().toISOString(),
    comments: [
      { id: 'c1', authorName: 'David Chen', text: 'Investigating database pool limits.', isInternal: true, createdAt: new Date().toISOString() }
    ],
    history: [
      { actor: 'Sarah Jenkins', action: 'Created Incident', timestamp: new Date().toISOString() }
    ]
  },
  {
    id: 'INC-89211',
    title: 'PostgreSQL Primary Node Memory Leak',
    category: 'Database',
    description: 'Memory usage spiking to 96% on primary database cluster.',
    priority: 'HIGH',
    status: 'ASSIGNED',
    impact: 'Department',
    urgency: 'High',
    reporterId: 'u-admin',
    reporterName: 'Alex Morgan',
    assigneeId: 'u-agent1',
    assigneeName: 'David Chen',
    createdAt: new Date().toISOString(),
    comments: [],
    history: []
  },
  {
    id: 'INC-89212',
    title: 'Auth Service Latency Degradation',
    category: 'Authentication',
    description: 'JWT token validation taking over 1.2s per request.',
    priority: 'MEDIUM',
    status: 'OPEN',
    impact: 'Department',
    urgency: 'Medium',
    reporterId: 'u-user1',
    reporterName: 'Sarah Jenkins',
    createdAt: new Date().toISOString(),
    comments: [],
    history: []
  }
];

export const initialKBArticles = [
  {
    id: 'kb-101',
    title: 'Database Connection Pool Exhaustion Fix',
    category: 'Database',
    summary: 'Increased maximum connection pool size from 20 to 100 in application.yml.',
    rootCause: 'High traffic volume caused connection leak under peak checkout load.',
    resolutionSteps: 'Increased pool size and configured maxLifetime to 30 minutes.',
    tags: ['Database', 'PostgreSQL', 'Pool']
  }
];
