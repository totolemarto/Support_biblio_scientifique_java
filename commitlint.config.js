module.exports = {
    extends: ['@commitlint/config-conventional'],

    rules: {
        'type-enum': [2, 'always', [
            'feat',
            'fix',
            'perf',
            'refactor',
            'test',
            'docs',
            'chore',
            'ci',
            'build',
            'revert',
        ]],

        'type-case':  [2, 'always', 'lower-case'],
        'type-empty': [2, 'never'],

        'subject-empty':     [2, 'never'],
        'subject-full-stop': [2, 'never', '.'],
        'subject-case':      [1, 'always', 'lower-case'],

        'header-max-length': [1, 'always', 100],
        'scope-case': [2, 'always', 'lower-case'],
    },
};