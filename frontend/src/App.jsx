import React, { useState } from 'react';
import Header from './components/Header';
import Sidebar from './components/Sidebar';
import AnalyticsDashboard from './components/Dashboard/AnalyticsDashboard';
import IncidentKanban from './components/Incidents/IncidentKanban';
import IncidentList from './components/Incidents/IncidentList';
import KnowledgeBaseView from './components/KnowledgeBase/KnowledgeBaseView';
import TeamWorkload from './components/Team/TeamWorkload';
import { IncidentProvider } from './context/IncidentContext';

export default function App() {
  const [activeTab, setActiveTab] = useState('dashboard');
  const [viewMode, setViewMode] = useState('kanban'); // 'kanban' or 'table'

  return (
    <IncidentProvider>
      <div className="flex h-screen bg-slate-950 text-slate-100 overflow-hidden">
        {/* Sidebar Navigation */}
        <Sidebar activeTab={activeTab} setActiveTab={setActiveTab} />

        {/* Main Content Area */}
        <div className="flex-1 flex flex-col overflow-hidden">
          <Header activeTab={activeTab} viewMode={viewMode} setViewMode={setViewMode} />

          <main className="flex-1 overflow-y-auto p-6 bg-slate-900/50">
            {activeTab === 'dashboard' && <AnalyticsDashboard />}
            {activeTab === 'incidents' && (
              viewMode === 'kanban' ? <IncidentKanban /> : <IncidentList />
            )}
            {activeTab === 'knowledge-base' && <KnowledgeBaseView />}
            {activeTab === 'team' && <TeamWorkload />}
          </main>
        </div>
      </div>
    </IncidentProvider>
  );
}
