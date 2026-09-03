import React, { useState } from 'react';

export default function ResolutionModal({ incident, onClose, onResolve }) {
  const [rootCause, setRootCause] = useState('');
  const [actionTaken, setActionTaken] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onResolve({ rootCause, actionTaken, addToKB: true });
    onClose();
  };

  return (
    <div className="fixed inset-0 bg-slate-950/80 backdrop-blur-sm flex items-center justify-center z-50 p-4">
      <div className="w-full max-w-md bg-slate-900 border border-slate-800 rounded-xl p-6 space-y-4">
        <h2 className="text-sm font-bold text-slate-100">Resolve Incident</h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <div>
            <label className="text-xs text-slate-400">Root Cause Analysis (RCA)</label>
            <textarea
              required
              className="w-full bg-slate-950 border border-slate-800 rounded-lg p-2 text-xs text-slate-100 mt-1"
              value={rootCause}
              onChange={(e) => setRootCause(e.target.value)}
            />
          </div>
          <button type="submit" className="w-full bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold py-2 rounded-lg text-xs">
            Confirm Resolution
          </button>
        </form>
      </div>
    </div>
  );
}
