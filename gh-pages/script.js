async function loadMetrics() {
    try {
        const res = await fetch('./metrics.json');
        const data = await res.json();

        document.getElementById('coverage').textContent = data.coverage + "%";
        document.getElementById('tests').textContent = data.tests;
        document.getElementById('status').textContent = data.status;

    } catch (e) {
        console.warn("Metrics not available");
    }
}

loadMetrics();