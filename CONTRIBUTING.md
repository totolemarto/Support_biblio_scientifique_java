# Contributing to YASCL

## Table of contents

- [Workflow git](#workflow-git)
- [Branches](#branches)
- [Conventional Commits](#conventional-commits)
- [Pull Requests](#pull-requests)
- [Code quality](#code-quality)
- [Releases](#releases)

---

## Workflow git

All significant changes go through a feature branch and a Pull Request validated by the other team member.

**Direct pushes to `main` are blocked.**

```
feature/my-feature
       │
       │  Pull Request (reviewed + approved)
       ▼
  development          ← integration branch (optional)
       │
       │  Pull Request
       ▼
     main              ← protected, no direct push
       │
       │  GitHub Release (manual merge of release PR)
       ▼
   vX.Y.Z tag
```

---

## Branches

| Type          | Naming           | Example                   |
|---------------|------------------|---------------------------|
| Feature       | `feature/<name>` | `feature/ndarray-reshape` |
| Bug fix       | `fix/<name>`     | `fix/addition-overflow`   |
| Documentation | `docs/<name>`    | `docs/update-readme`      |
| CI / tooling  | `chore/<name>`   | `chore/add-dependabot`    |

Branch names must be lowercase, with hyphens as separators.

---

## Conventional Commits

This project enforces [Conventional Commits](https://www.conventionalcommits.org/). Messages that do not follow the convention will be rejected by CI on PRs targeting `main` or `development`.

### Format

```
<type>(<optional scope>): <short description>

<optional body>

<optional footer>
```

### Allowed types

| Type       | When to use                              | Version bump    |
|------------|------------------------------------------|-----------------|
| `feat`     | New feature                              | `minor` (0.x.0) |
| `fix`      | Bug fix                                  | `patch` (0.0.x) |
| `perf`     | Performance improvement                  | `patch` (0.0.x) |
| `refactor` | Refactoring, no behaviour change         | none            |
| `test`     | Adding or updating tests                 | none            |
| `docs`     | Documentation only                       | none            |
| `chore`    | Maintenance, dependencies, configuration | none            |
| `ci`       | CI/CD changes                            | none            |
| `build`    | Build system (Maven, etc.)               | none            |
| `revert`   | Reverts a previous commit                | none            |

A `BREAKING CHANGE:` footer triggers a `major` bump (x.0.0) regardless of type.

Adding a `!` after the type (e.g. `feat!:`) also indicates a breaking change.

### Examples

```
feat(ndarray): add reshape() method

fix(operations): fix integer overflow in element-wise addition

test(ndarray): add edge cases for zeros() with size 0

chore(deps): bump allure-junit5 from 2.29.0 to 2.29.1

feat!: remove deprecated NdArray constructor

BREAKING CHANGE: the NdArray(int) constructor has been removed.
Use NdArray.zeros(int) instead.
```

### Rules

- The description must be in lowercase and must not end with a period
- The scope, if present, must be in lowercase
- Commit titles should be under 100 characters

---

## Pull Requests

### Before opening a PR

- [ ] Tests pass locally: `mvn clean verify`
- [ ] Coverage stays above 80%: `mvn verify -Pci` (JaCoCo gate is enforced in CI)
- [ ] Mutation score stays above 70%: `mvn verify org.pitest:pitest-maven:mutationCoverage -Pci`
- [ ] Javadoc is up to date for any new or modified public method
- [ ] Commit messages follow Conventional Commits

### Review rules

- Every PR requires **approval from the other team member** before merging
- The author must not merge their own PR
- A PR may be merged once CI is green and the review is approved

---

## Code quality

The following thresholds are enforced in CI and will block a merge if not met:

| Metric         | Threshold | Tool      |
|----------------|-----------|-----------|
| Line coverage  | ≥ 80%     | JaCoCo    |
| Mutation score | ≥ 70%     | PIT       |
| Quality gate   | pass      | SonarQube |

Reports are published to [GitHub Pages](https://totolemarto.github.io/Support_biblio_scientifique_java/) after each merge to `main`.

---

## Releases

Releases are managed automatically by [`release-please`](https://github.com/googleapis/release-please).

1. After merging one or more `feat:` or `fix:` commits to `main`, `release-please` opens a release PR containing a version bump in `pom.xml` and an updated `CHANGELOG.md`.
2. Review and merge the release PR when the team is ready to publish.
3. `release-please` creates the GitHub Release and tag automatically.
4. The `release-docs` and `mvn-deploy` workflows trigger automatically on the new release.

**Do not create releases or tags manually.**