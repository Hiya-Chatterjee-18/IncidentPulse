import React from 'react';
import { Clock, AlertTriangle } from 'lucide-react';

export default function SLATimerBadge({ deadline, isBreached }) {
  if (isBreached) {
    return (
      <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded text-[10px] font-semibold bg-rose-500/20 text-rose-300 border border-rose-500/40">
        <AlertTriangle className="w-3 h-3 text-rose-400" />
        SLA Breached
      </span>
    );
  }

  return (
    <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded text-[10px] font-medium bg-slate-800 text-slate-300 border border-slate-700">
      <Clock className="w-3 h-3 text-cyan-400" />
      SLA Active
    </span>
  );
}
