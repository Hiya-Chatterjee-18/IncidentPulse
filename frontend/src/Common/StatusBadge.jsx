import React from 'react';

export default function StatusBadge({ status }) {
  const styles = {
    OPEN: 'bg-cyan-500/10 text-cyan-400 border-cyan-500/30',
    ASSIGNED: 'bg-purple-500/10 text-purple-400 border-purple-500/30',
    IN_PROGRESS: 'bg-blue-500/10 text-blue-400 border-blue-500/30',
    RESOLVED: 'bg-emerald-500/10 text-emerald-400 border-emerald-500/30',
    CLOSED: 'bg-slate-700/40 text-slate-400 border-slate-700',
  };

  return (
    <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-[10px] font-semibold border ${styles[status] || styles.OPEN}`}>
      {status ? status.replace('_', ' ') : 'OPEN'}
    </span>
  );
}
