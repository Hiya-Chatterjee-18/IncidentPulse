import React from 'react';
import { useIncidentContext } from '../../context/IncidentContext';
import PriorityBadge from '../Common/PriorityBadge';
import StatusBadge from '../Common/StatusBadge';
import { X, CheckCircle2, UserCheck, MessageSquare } from 'lucide-react';

export default function IncidentDetail({ incident, onClose }) {
  const { updateStatus } = useIncidentContext();

  return (
    <div className="fixed inset-0 bg-slate-950/80 backdrop-blur-sm flex justify-end z-50">
      <div className="w-full max-w-xl bg-slate-900 border-l border-slate-800 p-6 h-full overflow-y-auto space-y-6">
        <div className="flex items-center justify-between border-b border-slate-800 pb-4">
          <div>
            <span className="text-xs font-mono text-cyan-400">{incident.id}</span>
            <h2 className="text-lg font-bold text-slate-100">{incident.title}</h2>
          </div>
          <button onClick={onClose} className="p-2 rounded-lg hover:bg-slate-800 text-slate-400">
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="flex items-center gap-3">
          <PriorityBadge priority={incident.priority} />
          <StatusBadge status={incident.status} />
        </div>

        <div className="space-y-2">
          <h3 className="text-xs font-bold text-slate-400 uppercase">Description</h3>
          <p className="text-xs text-slate-300 leading-relaxed bg-slate-950 p-4 rounded-lg border border-slate-800">
            {incident.description}
          </p>
        </div>
      </div>
    </div>
  );
}
