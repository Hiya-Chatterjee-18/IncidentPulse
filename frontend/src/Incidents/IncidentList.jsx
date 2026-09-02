import React from 'react';
import { useIncidentContext } from '../../context/IncidentContext';
import PriorityBadge from '../Common/PriorityBadge';
import StatusBadge from '../Common/StatusBadge';

export default function IncidentList() {
  const { incidents, setSelectedIncident } = useIncidentContext();

  return (
    <div className="glass-panel rounded-xl border border-slate-800 overflow-hidden">
      <table className="w-full text-left border-collapse text-xs">
        <thead>
          <tr className="border-b border-slate-800 bg-slate-900/80 text-slate-400 font-semibold">
            <th className="p-3">ID</th>
            <th className="p-3">Title</th>
            <th className="p-3">Category</th>
            <th className="p-3">Priority</th>
            <th className="p-3">Status</th>
            <th className="p-3">Assignee</th>
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-800/50">
          {incidents.map((incident) => (
            <tr
              key={incident.id}
              onClick={() => setSelectedIncident(incident)}
              className="hover:bg-slate-900/60 cursor-pointer transition-colors"
            >
              <td className="p-3 font-mono text-cyan-400">{incident.id}</td>
              <td className="p-3 font-medium text-slate-200">{incident.title}</td>
              <td className="p-3 text-slate-400">{incident.category}</td>
              <td className="p-3"><PriorityBadge priority={incident.priority} /></td>
              <td className="p-3"><StatusBadge status={incident.status} /></td>
              <td className="p-3 text-slate-400">{incident.assigneeName || 'Unassigned'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
