import React, { useState } from 'react';
import { BookOpen, Search, Tag, Clock } from 'lucide-react';

export default function KnowledgeBaseView() {
  const [searchTerm, setSearchTerm] = useState('');

  const articles = [
    {
      id: 'kb-101',
      title: 'Database Connection Pool Exhaustion Fix',
      category: 'Database',
      summary: 'Increased maximum connection pool size from 20 to 100 in application.yml.',
      rootCause: 'High traffic volume caused connection leak under peak checkout load.',
      resolutionSteps: 'Increased pool size and configured maxLifetime to 30 minutes.',
      tags: ['Database', 'PostgreSQL', 'Pool']
    },
    {
      id: 'kb-102',
      title: 'Payment Gateway Timeout Mitigation',
      category: 'Payment Gateway',
      summary: 'Configured retry circuit breaker pattern for external payment provider API.',
      rootCause: 'Third-party gateway latency caused thread blocking.',
      resolutionSteps: 'Added 3-second timeout circuit breaker fallback.',
      tags: ['Payment', 'API', 'CircuitBreaker']
    }
  ];

  const filteredArticles = articles.filter(a =>
    a.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
    a.category.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="space-y-6">
      {/* Search Header */}
      <div className="glass-panel p-4 rounded-xl border border-slate-800 flex items-center gap-3">
        <Search className="w-5 h-5 text-slate-400" />
        <input
          type="text"
          placeholder="Search Knowledge Base articles, RCA solutions, and tags..."
          className="w-full bg-transparent text-xs text-slate-100 placeholder-slate-500 focus:outline-none"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      {/* Articles Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {filteredArticles.map((article) => (
          <div key={article.id} className="glass-panel glass-panel-hover p-5 rounded-xl border border-slate-800 space-y-3">
            <div className="flex items-center justify-between">
              <span className="text-[10px] font-mono text-cyan-400">{article.id}</span>
              <span className="text-[10px] font-semibold bg-purple-500/10 text-purple-400 px-2 py-0.5 rounded border border-purple-500/20">
                {article.category}
              </span>
            </div>
            <h3 className="text-sm font-bold text-slate-100">{article.title}</h3>
            <p className="text-xs text-slate-400 bg-slate-950/60 p-3 rounded-lg border border-slate-800/80">
              {article.summary}
            </p>
            <div className="flex flex-wrap gap-1.5 pt-2">
              {article.tags.map((tag, idx) => (
                <span key={idx} className="inline-flex items-center gap-1 px-2 py-0.5 rounded text-[10px] bg-slate-800 text-slate-400">
                  <Tag className="w-2.5 h-2.5" />
                  {tag}
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
