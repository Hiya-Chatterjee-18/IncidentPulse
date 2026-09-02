import React from 'react';
import { LayoutDashboard, AlertCircle, BookOpen, Users, ShieldAlert, Cpu } from 'lucide-react';

export default function Sidebar({ activeTab, setActiveTab }) {
  const navItems = [
    { id: 'dashboard', label: 'Analytics', icon: LayoutDashboard },
    { id: 'incidents', label: 'Incidents', icon: AlertCircle },
    { id: 'knowledge-base', label: 'Knowledge Base', icon: BookOpen },
    { id: 'team', label: 'Team Workload', icon: Users },
  ];

  return (
    <aside className="w-64 bg-slate-950 border-r border-slate-800 flex flex-col justify-between p-4 select-none">
      <div>
        {/* Brand Logo */}
        <div className="flex items-center gap-3 px-3 py-3 mb-6 border-b border-slate-800">
          <div className="w-9 h-9 rounded-xl bg-gradient-to-br from-cyan-500 to-blue-600 flex items-center justify-center shadow-lg shadow-cyan-500/20">
            <ShieldAlert className="w-5 h-5 text-white" />
          </div>
          <div>
            <h2 className="font-bold text-sm text-slate-100 tracking-wide">Incident Platform</h2>
            <span className="text-[10px] text-cyan-400 font-mono tracking-wider">JAVA 21 &bull; SPARK ETL</span>
          </div>
        </div>

        {/* Navigation Items */}
        <nav className="space-y-1">
          {navItems.map((item) => {
            const Icon = item.icon;
            const isActive = activeTab === item.id;
            return (
              <button
                key={item.id}
                onClick={() => setActiveTab(item.id)}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-xs font-medium transition-all ${
                  isActive
                    ? 'bg-cyan-500/10 text-cyan-400 border border-cyan-500/20 shadow-sm shadow-cyan-500/10'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900/60'
                }`}
              >
                <Icon className={`w-4 h-4 ${isActive ? 'text-cyan-400' : 'text-slate-400'}`} />
                {item.label}
              </button>
            );
          })}
        </nav>
      </div>

      {/* Footer System Status */}
      <div className="p-3 rounded-lg bg-slate-900/60 border border-slate-800/80">
        <div className="flex items-center justify-between text-[11px] mb-1">
          <span className="text-slate-400 flex items-center gap-1">
            <Cpu className="w-3 h-3 text-emerald-400 animate-pulse" /> Stream Pipeline
          </span>
          <span className="text-emerald-400 font-mono font-semibold">Kafka Active</span>
        </div>
        <div className="w-full bg-slate-800 h-1.5 rounded-full overflow-hidden">
          <div className="bg-emerald-400 h-full w-[94%]"></div>
        </div>
      </div>
    </aside>
  );
}
