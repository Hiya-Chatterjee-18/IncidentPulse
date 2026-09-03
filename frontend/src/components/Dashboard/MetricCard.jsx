import React from 'react';

export default function MetricCard({ title, value, icon: Icon, trend, color = 'cyan' }) {
  const colorStyles = {
    cyan: 'from-cyan-500/20 to-blue-500/10 text-cyan-400 border-cyan-500/20',
    rose: 'from-rose-500/20 to-pink-500/10 text-rose-400 border-rose-500/20',
    emerald: 'from-emerald-500/20 to-teal-500/10 text-emerald-400 border-emerald-500/20',
    purple: 'from-purple-500/20 to-indigo-500/10 text-purple-400 border-purple-500/20',
  };

  return (
    <div className={`glass-panel p-5 rounded-xl border bg-gradient-to-br ${colorStyles[color]}`}>
      <div className="flex items-center justify-between mb-3">
        <span className="text-xs font-semibold text-slate-400 uppercase tracking-wider">{title}</span>
        <div className="p-2 rounded-lg bg-slate-900/80 border border-slate-800">
          <Icon className="w-4 h-4" />
        </div>
      </div>
      <div className="text-2xl font-bold text-slate-100 mb-1">{value}</div>
      <div className="text-[11px] font-medium text-slate-400">{trend}</div>
    </div>
  );
}
