import React from 'react';
import { LayoutGrid, List, Plus, ShieldAlert, Sparkles } from 'lucide-react';
import RoleSwitcher from './RoleSwitcher';

export default function Header({ activeTab, viewMode, setViewMode, onNewIncident }) {
  const getTitle = () => {
    switch (activeTab) {
      case 'dashboard': return 'Operational Analytics Dashboard';
      case 'incidents': return 'Incident Command Center';
      case 'knowledge-base': return 'Knowledge Base & RCA Library';
      case 'team': return 'Support Team Workload';
      default: return 'Smart Incident Management';
    }
  };

  return (
    <header className="h-16 border-b border-slate-800 bg-slate-950/80 px-6 flex items-center justify-between backdrop-blur-md sticky top-0 z-30">
      <div className="flex items-center gap-3">
        <h1 className="text-xl font-bold text-slate-100 flex items-center gap-2">
          {getTitle()}
        </h1>
      </div>

      <div className="flex items-center gap-4">
        {activeTab === 'incidents' && (
          <div className="flex items-center bg-slate-900 border border-slate-800 rounded-lg p-1">
            <button
              onClick={() => setViewMode('kanban')}
              className={`flex items-center gap-1.5 px-3 py-1.5 rounded-md text-xs font-medium transition-all ${
                viewMode === 'kanban' ? 'bg-cyan-500/20 text-cyan-400 border border-cyan-500/30' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              <LayoutGrid className="w-3.5 h-3.5" />
              Kanban
            </button>
            <button
              onClick={() => setViewMode('table')}
              className={`flex items-center gap-1.5 px-3 py-1.5 rounded-md text-xs font-medium transition-all ${
                viewMode === 'table' ? 'bg-cyan-500/20 text-cyan-400 border border-cyan-500/30' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              <List className="w-3.5 h-3.5" />
              List
            </button>
          </div>
        )}

        <RoleSwitcher />
      </div>
    </header>
  );
}
