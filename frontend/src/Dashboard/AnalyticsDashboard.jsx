import React from 'react';
import { useIncidentContext } from '../../context/IncidentContext';
import MetricCard from './MetricCard';
import { AlertCircle, CheckCircle2, Clock, ShieldCheck, Activity, BarChart3, Database } from 'lucide-react';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ArcElement } from 'chart.js';
import { Bar, Doughnut } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ArcElement);

export default function AnalyticsDashboard() {
  const { incidents } = useIncidentContext();

  const totalIncidents = incidents.length;
  const openIncidents = incidents.filter(i => i.status === 'OPEN' || i.status === 'ASSIGNED' || i.status === 'IN_PROGRESS').length;
  const resolvedIncidents = incidents.filter(i => i.status === 'RESOLVED' || i.status === 'CLOSED').length;
  const criticalCount = incidents.filter(i => i.priority === 'CRITICAL').length;

  const barData = {
    labels: ['Database', 'Backend', 'Payment', 'Cloud', 'Auth'],
    datasets: [
      {
        label: 'Incidents by Category',
        data: [12, 19, 8, 15, 6],
        backgroundColor: 'rgba(6, 182, 212, 0.6)',
        borderColor: 'rgb(6, 182, 212)',
        borderWidth: 1,
        borderRadius: 6,
      },
    ],
  };

  const doughnutData = {
    labels: ['Critical', 'High', 'Medium', 'Low'],
    datasets: [
      {
        data: [criticalCount, 8, 14, 10],
        backgroundColor: ['#f43f5e', '#fbbf24', '#facc15', '#34d399'],
        borderWidth: 0,
      },
    ],
  };

  return (
    <div className="space-y-6">
      {/* Metric Cards Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <MetricCard title="Total Volume" value={totalIncidents} icon={Activity} trend="+12% from last week" color="cyan" />
        <MetricCard title="Active Outages" value={openIncidents} icon={AlertCircle} trend="Requires Attention" color="rose" />
        <MetricCard title="Resolved Incidents" value={resolvedIncidents} icon={CheckCircle2} trend="98.4% SLA Compliance" color="emerald" />
        <MetricCard title="Mean Time To Resolve" value="2.4 hrs" icon={Clock} trend="Sub-15ms Redis Cache" color="purple" />
      </div>

      {/* Analytics Charts Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2 glass-panel p-5 rounded-xl border border-slate-800">
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-sm font-bold text-slate-100 flex items-center gap-2">
              <BarChart3 className="w-4 h-4 text-cyan-400" /> Category Outage Volume
            </h3>
            <span className="text-[10px] font-mono bg-cyan-500/10 text-cyan-400 px-2 py-0.5 rounded border border-cyan-500/20">PYSPARK ETL OUTPUT</span>
          </div>
          <div className="h-64">
            <Bar data={barData} options={{ responsive: true, maintainAspectRatio: false }} />
          </div>
        </div>

        <div className="glass-panel p-5 rounded-xl border border-slate-800">
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-sm font-bold text-slate-100 flex items-center gap-2">
              <Database className="w-4 h-4 text-purple-400" /> Priority Distribution
            </h3>
          </div>
          <div className="h-64 flex items-center justify-center">
            <Doughnut data={doughnutData} options={{ responsive: true, maintainAspectRatio: false }} />
          </div>
        </div>
      </div>
    </div>
  );
}
