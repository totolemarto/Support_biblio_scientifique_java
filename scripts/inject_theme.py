#!/usr/bin/env python3

import sys
import argparse
from pathlib import Path

BANNER_SCRIPT = """<script>
(function () {
    var parts = window.location.pathname.split('/').filter(Boolean);
    if (parts.length < 2) return;
    var repoRoot = '/' + parts[0] + '/';
    var currentVersion = parts[1];
    if (!currentVersion || currentVersion === 'dev') return;

    fetch(repoRoot + 'versions.json')
        .then(function (r) { return r.json(); })
        .then(function (data) {
            if (currentVersion === data.latest) return;

            var banner = document.createElement('div');
            banner.style.cssText = [
                'position:fixed', 'top:0', 'left:0', 'right:0', 'z-index:9999',
                'background:#7c2d12', 'color:#fed7aa',
                'padding:10px 20px', 'text-align:center',
                'font-family:system-ui,sans-serif', 'font-size:0.9rem',
                'box-shadow:0 2px 8px rgba(0,0,0,0.5)'
            ].join(';');

            var latestUrl = repoRoot + data.latest + '/';
            banner.innerHTML =
                'Outdated version\u00a0: <strong>' + currentVersion + '</strong> - ' +
                '<a href="' + latestUrl + '" style="color:#fdba74;font-weight:bold;">' +
                'Upgrade to the latest version (' + data.latest + ')</a>';

            document.body.prepend(banner);
            document.body.style.marginTop = '44px';
        })
        .catch(function () {});
})();
</script>"""

INJECTION_MARKER = "<!-- yascl-injected -->"


def inject(directory: str, css_file: str, add_banner: bool = False) -> None:
    root = Path(directory)
    if not root.is_dir():
        print(f"Error : '{directory}' is not a valid directory.", file=sys.stderr)
        sys.exit(1)

    css_content = Path(css_file).read_text(encoding="utf-8")
    style_block = f"{INJECTION_MARKER}\n<style>\n{css_content}\n</style>"

    processed = 0
    skipped = 0

    for html_file in sorted(root.rglob("*.html")):
        content = html_file.read_text(encoding="utf-8", errors="ignore")

        if INJECTION_MARKER in content:
            skipped += 1
            continue

        if "</head>" in content:
            content = content.replace("</head>", f"{style_block}\n</head>", 1)
        else:
            content = style_block + "\n" + content

        if add_banner and "</body>" in content:
            content = content.replace("</body>", f"{BANNER_SCRIPT}\n</body>", 1)

        html_file.write_text(content, encoding="utf-8")
        processed += 1

    print(f"{processed} file(s) processed, {skipped} file(s) skipped (already injected).")


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("directory")
    parser.add_argument("css_file")
    parser.add_argument("--banner", action="store_true")
    args = parser.parse_args()
    inject(args.directory, args.css_file, args.banner)