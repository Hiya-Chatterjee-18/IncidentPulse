import React from 'react';
import { Users, CheckCircle, Clock } from 'lucide-react';

export default function TeamWorkload() {
  const teamMembers = [
    { name: 'David Chen', role: 'Support Agent', specialty: 'Database & Backend', assigned: 3, resolved: 14, avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=David' },
    { name: 'Alex Morgan', role: 'IT Manager', specialty: 'System Admin & Cloud', assigned: 1, resolved: 22, avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Alex' },
    { name: 'Sarah Jenkins', role: 'Support Specialist', specialty: 'Frontend & API Gateway', assigned: 2, resolved: 18, avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Sarah' },
  ];

  return (
    <div className="space-y-6">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {teamMembers.map((member, idx) => (
          <div key={idx} className="glass-panel p-5 rounded-xl border border-slate-800 space-y-4">
            <div className="flex items-center gap-3">
              <img src={member.avatar} alt={member.name} className="w-10 h-10 rounded-xl bg-slate-800 p-1 border border-slate-700" />
              <div>
                <h3 className="text-sm font-bold text-slate-100">{member.name}</h3>
                <span className="text-[11px] text-cyan-400 font-medium">{member.specialty}</span>
              </div>
            </div>
            <div className="grid grid-cols-2 gap-2 pt-2 border-t border-slate-800/80">
              <div className="bg-slate-950 p-2.5 rounded-lg border border-slate-800">
                <span className="text-[10px] text-slate-400 block">Active Incidents</span>
                <span className="text-sm font-bold text-amber-400">{member.assigned}</span>
              </div>
              <div className="bg-slate-950 p-2.5 rounded-lg border border-slate-800">
                <span className="text-[10px] text-slate-400 block">Resolved</span>
                <span className="text-sm font-bold text-emerald-400">{member.resolved}</span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
