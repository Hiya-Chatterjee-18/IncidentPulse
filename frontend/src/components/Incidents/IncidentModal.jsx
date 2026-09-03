import React, { useState } from 'react';
import { useIncidentContext } from '../../context/IncidentContext';
import { X } from 'lucide-react';

export default function IncidentModal({ onClose }) {
  const { createIncident } = useIncidentContext();
  const [formData, setFormData] = useState({
    title: '',
    category: 'Database',
    description: '',
    priority: 'HIGH',
    urgency: 'High',
    impact: 'Department',
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    createIncident(formData);
    onClose();
  };

  return (
    <div className="fixed inset-0 bg-slate-950/80 backdrop-blur-sm flex items-center justify-center z-50 p-4">
      <div className="w-full max-w-md bg-slate-900 border border-slate-800 rounded-xl p-6 space-y-4">
        <div className="flex items-center justify-between border-b border-slate-800 pb-3">
          <h2 className="text-sm font-bold text-slate-100">Report New Incident</h2>
          <button onClick={onClose} className="text-slate-400 hover:text-white"><X className="w-4 h-4" /></button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-3">
          <div>
            <label className="text-xs font-medium text-slate-400">Title</label>
            <input
              type="text"
              required
              className="w-full bg-slate-950 border border-slate-800 rounded-lg p-2 text-xs text-slate-100 mt-1"
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
            />
          </div>
          <button type="submit" className="w-full bg-cyan-500 hover:bg-cyan-400 text-slate-950 font-bold py-2 rounded-lg text-xs mt-4">
            Submit Incident
          </button>
        </form>
      </div>
    </div>
  );
}
