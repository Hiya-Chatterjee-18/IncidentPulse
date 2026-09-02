import React from 'react';
import { useIncidentContext } from '../context/IncidentContext';
import { UserCheck } from 'lucide-react';

export default function RoleSwitcher() {
  const { currentRole, setCurrentRole } = useIncidentContext();

  const roles = [
    { id: 'REPORTER', label: 'Employee' },
    { id: 'AGENT', label: 'Support Agent' },
    { id: 'ADMIN', label: 'IT Manager' },
  ];

  return (
    <div className="flex items-center gap-2 bg-slate-900 border border-slate-800 rounded-lg p-1">
      <UserCheck className="w-3.5 h-3.5 text-cyan-400 ml-2" />
      <span className="text-xs text-slate-400 font-medium mr-1">Role:</span>
      {roles.map((role) => (
        <button
          key={role.id}
          onClick={() => setCurrentRole(role.id)}
          className={`px-2.5 py-1 rounded text-[11px] font-semibold transition-all ${
            currentRole === role.id
              ? 'bg-cyan-500 text-slate-950 shadow-sm'
              : 'text-slate-400 hover:text-slate-200'
          }`}
        >
          {role.label}
        </button>
      ))}
    </div>
  );
}
