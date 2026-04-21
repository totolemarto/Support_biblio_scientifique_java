const SECTIONS = {
    'link-javadoc':  'javadoc/index.html',
    'link-tests':    'tests/index.html',
    'link-coverage': 'coverage/index.html',
    'link-mutation': 'mutation/index.html',
};

async function loadMetrics() {
    try {
        const res = await fetch('./metrics.json');
        const data = await res.json();

        document.getElementById('version').textContent = data.version ?? '--';
        document.getElementById('coverage').textContent = (data.coverage ?? '--') + '%';
        document.getElementById('tests').textContent = data.tests ?? '--';
        document.getElementById('status').textContent = data.status ?? '--';
        document.getElementById('lastUpdated').textContent = data.timestamp ? new Date(data.timestamp * 1000).toLocaleString() : '--';
    } catch {
        console.warn('metrics.json not available');
    }
}

async function loadVersionSwitcher() {
    try {
        const res  = await fetch('./versions.json');
        const data = await res.json();

        const select = document.getElementById('version-select');
        const badge  = document.getElementById('version-badge');

        select.innerHTML = '';

        data.versions.forEach(v => {
            const opt = document.createElement('option');
            opt.value       = v;
            opt.textContent = v === data.latest ? `${v} (latest)` : v;
            select.appendChild(opt);
        });

        const pathParts= new Set(globalThis.location.pathname.split('/').filter(Boolean));
        const versionInUrl = data.versions.find(v => pathParts.has(v));
        const active= versionInUrl ?? 'dev';

        select.value = active;
        updateLinks(active);

        if (active === data.latest) {
            badge.style.display = 'inline';
        }

        select.addEventListener('change', () => {
            const chosen = select.value;
            updateLinks(chosen);

            badge.style.display = (chosen === data.latest) ? 'inline' : 'none';

            const currentSection = detectCurrentSection();
            globalThis.location.href = currentSection
                ? `./${chosen}/${currentSection}`
                : `./${chosen}/`;
        });

    } catch {
        console.warn('versions.json not available - version switcher disabled');

        const select = document.getElementById('version-select');
        select.innerHTML = '<option value="dev">dev</option>';
        updateLinks('dev');
    }
}

function updateLinks(version) {
    for (const [id, path] of Object.entries(SECTIONS)) {
        const el = document.getElementById(id);
        if (el) el.href = `./${version}/${path}`;
    }
}

function detectCurrentSection() {
    const path = globalThis.location.pathname;
    return Object.values(SECTIONS).map(s => s.split('/')[0])
        .find(section => path.includes(`/${section}/`)) ?? '';
}

try {
    await loadMetrics();
    await loadVersionSwitcher();
} catch (error) {
    console.error(error);
    exit(1);
}