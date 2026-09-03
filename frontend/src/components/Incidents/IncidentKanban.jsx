import React from 'react';
import { useIncidentContext } from '../../context/IncidentContext';
import PriorityBadge from '../Common/PriorityBadge';
import StatusBadge from '../Common/StatusBadge';
import { Clock, User } from 'lucide-react';

export default function IncidentKanban() {
  const { incidents, setSelectedIncident } = useIncidentContext();

  const columns = [
    { id: 'OPEN', label: 'Open' },
    { id: 'ASSIGNED', label: 'Assigned' },
    { id: 'IN_PROGRESS', label: 'In Progress' },
    { id: 'RESOLVED', label: 'Resolved' },
  ];

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 h-full">
      {columns.map((column) => {
        const columnIncidents = incidents.filter(i => i.status === column.id);

        return (
          <div key={column.id} className="bg-slate-950/60 border border-slate-800/80 rounded-xl p-4 flex flex-col">
            <div className="flex items-center justify-between mb-3 pb-2 border-b border-slate-800">
              <h3 className="text-xs font-bold text-slate-300 uppercase tracking-wider">{column.label}</h3>
              <span className="text-[10px] font-mono bg-slate-800 text-slate-400 px-2 py-0.5 rounded-full">
                {columnIncidents.length}
              </span>
            </div>

            <div className="space-y-3 overflow-y-auto flex-1 pr-1">
              {columnIncidents.map((incident) => (
                <div
                  key={incident.id}
                  onClick={() => setSelectedIncident(incident)}
                  className="glass-panel glass-panel-hover p-4 rounded-xl cursor-pointer border border-slate-800/80 bg-slate-900/90"
                >
                  <div className="flex items-center justify-between mb-2">
                    <span className="text-[10px] font-mono text-cyan-400">{incident.id}</span>
                    <PriorityBadge priority={incident.priority} />
                  </div>
                  <h4 className="text-xs font-semibold text-slate-100 mb-2 line-clamp-2">{incident.title}</h4>
                  <div className="flex items-center justify-between text-[10px] text-slate-400 mt-3 pt-2 border-t border-slate-800/50">
                    <span className="flex items-center gap-1">
                      <User className="w-3 h-3 text-slate-500" />
                      {incident.assigneeName || 'Unassigned'}
                    </span>
                    <span className="flex items-center gap-1">
                      <Clock className="w-3 h-3 text-slate-500" />
                      SLA 4h
                    </span>
                  </div>
                </div>
              ))}
            </div>
          </div>
        );
      })}
    </div>
  );
}
