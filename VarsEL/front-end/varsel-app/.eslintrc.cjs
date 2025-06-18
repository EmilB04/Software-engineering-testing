module.exports = {
  // https://eslint.org/docs/user-guide/configuring#configuration-cascading-and-hierarchy
  // This option interrupts the configuration hierarchy at this file
  // Remove this if you have an higher level ESLint config file (it usually happens into a monorepos)
  root: true,

  // https://eslint.vuejs.org/user-guide/#how-to-use-a-custom-parser
  // Must use parserOptions instead of "parser" to allow vue-eslint-parser to keep working
  // `parser: 'vue-eslint-parser'` is already included with any 'plugin:vue/**' config and should be omitted
  parserOptions: {
    parser: require.resolve('@typescript-eslint/parser'),
    extraFileExtensions: [ '.vue' ]
  },

  env: {
    browser: true,
    es2021: true,
    node: true
  },

  // Rules order is important, please avoid shuffling them
  extends: [
    // Base ESLint recommended rules
    // 'eslint:recommended',

    // https://github.com/typescript-eslint/typescript-eslint/tree/master/packages/eslint-plugin#usage
    // ESLint typescript rules
    'plugin:@typescript-eslint/recommended',

    // Uncomment any of the lines below to choose desired strictness,
    // but leave only one uncommented!
    // See https://eslint.vuejs.org/rules/#available-rules
    'plugin:vue/vue3-essential', // Priority A: Essential (Error Prevention)
    // 'plugin:vue/vue3-strongly-recommended', // Priority B: Strongly Recommended (Improving Readability)
    // 'plugin:vue/vue3-recommended', // Priority C: Recommended (Minimizing Arbitrary Choices and Cognitive Overhead)

    'standard'

  ],

  plugins: [
    // required to apply rules which need type information
    '@typescript-eslint',

    // https://eslint.vuejs.org/user-guide/#why-doesn-t-it-work-on-vue-files
    // required to lint *.vue files
    'vue'

  ],

  globals: {
    ga: 'readonly', // Google Analytics
    cordova: 'readonly',
    __statics: 'readonly',
    __QUASAR_SSR__: 'readonly',
    __QUASAR_SSR_SERVER__: 'readonly',
    __QUASAR_SSR_CLIENT__: 'readonly',
    __QUASAR_SSR_PWA__: 'readonly',
    process: 'readonly',
    Capacitor: 'readonly',
    chrome: 'readonly'
  },

  rules: {

    // allow async-await
    'generator-star-spacing': 'off',
    // allow paren-less arrow functions
    'arrow-parens': 'off',
    'one-var': 'off',
    'no-void': 'off',
    'multiline-ternary': 'off',

    'import/first': 'off',
    'import/namespace': 'error',
    'import/default': 'error',
    'import/export': 'error',
    'import/extensions': 'off',
    'import/no-unresolved': 'off',
    'import/no-extraneous-dependencies': 'off',

    // The core 'import/named' rules
    // does not work with type definitions
    'import/named': 'off',

    'prefer-promise-reject-errors': 'off',

    quotes: ['warn', 'single', { avoidEscape: true }],

    // this rule, if on, would require explicit return type on the `render` function
    '@typescript-eslint/explicit-function-return-type': 'off',

    // in plain CommonJS modules, you can't use `import foo = require('foo')` to pass this rule, so it has to be disabled
    '@typescript-eslint/no-var-requires': 'off',

    // The core 'no-unused-vars' rules (in the eslint:recommended ruleset)
    // does not work with type definitions
    'no-unused-vars': 'off',

    // allow debugger during development only
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',

    //
    // Custom (https://gist.github.com/moander/d3cf569d45648bd2a1ac6105a19350bf)
    //

    'switch-colon-spacing': 'error',

    '@typescript-eslint/no-for-in-array': 'warn',
    '@typescript-eslint/type-annotation-spacing': 'error',

    'brace-style': 'off',
    '@typescript-eslint/brace-style': ['error', 'stroustrup'],

    'arrow-spacing': 'error',
    'vue/arrow-spacing': 'error',

    'comma-spacing': 'off',
    'vue/comma-spacing': 'error',
    '@typescript-eslint/comma-spacing': 'error',

    'keyword-spacing': 'off',
    'vue/keyword-spacing': 'error',
    '@typescript-eslint/keyword-spacing': 'error',

    'object-curly-spacing': 'off',
    'vue/object-curly-spacing': ['error', 'always'],
    '@typescript-eslint/object-curly-spacing': ['error', 'always'],

    'space-infix-ops': 'off',
    'vue/space-infix-ops': 'error',
    '@typescript-eslint/space-infix-ops': 'error',

    'vue/no-unused-components': 'warn',

    '@typescript-eslint/member-delimiter-style': ['error', {
      multiline: { delimiter: 'none', requireLast: true },
      singleline: { delimiter: 'semi', requireLast: false },
      multilineDetection: 'brackets',
    }],

    'comma-dangle': ['warn', 'always-multiline'],
    'space-before-function-paren': ['error', {
      anonymous: 'always',
      named: 'never',
      asyncArrow: 'always',
    }],
    '@typescript-eslint/space-before-function-paren': ['error', {
      anonymous: 'always',
      named: 'never',
      asyncArrow: 'always',
    }],
    '@typescript-eslint/no-unused-vars': ['warn', {
      argsIgnorePattern: '.',
      varsIgnorePattern: '^(props|emit|sessionStore|entityMeta|entityType|modelFieldsMap|AutoFormField|cr|\\$t)$',
      caughtErrorsIgnorePattern: '.',
    }],
    '@typescript-eslint/no-empty-function': 'warn',
    '@typescript-eslint/no-explicit-any': 'off',
    '@typescript-eslint/no-non-null-assertion': 'off',
    'no-unreachable': 'warn',
    'space-before-function-parent': 'off',
    'no-return-assign': 'off',
    'no-empty': 'warn',
    'no-useless-return': 'off',

    'sort-imports': 'off', // use import/order instead

    'import/order': [
      'error',
      {
        pathGroups: [
          { pattern: 'components/**', group: 'index' },
          { pattern: 'src/components/**', group: 'index' },
          { pattern: 'src/pages/**', group: 'index' },
          { pattern: 'src/layouts/**', group: 'index' },
          { pattern: 'src/**', group: 'internal' },
        ],
        pathGroupsExcludedImportTypes: ['index'],
        'newlines-between': 'always',
        alphabetize: {
          order: 'asc',
          caseInsensitive: true,
        },
      },
    ],

    'vue/no-undef-components': ['error', {
      ignorePatterns: [
        /^router-view$/,
        /^q-/, // ignore quasar components
      ],
    }],

    'vue/no-unused-vars': 'warn',

    'vue/html-indent': ['error', 2, {
      attribute: 1,
      baseIndent: 1,
      closeBracket: 0,
      alignAttributesVertically: false,
      ignores: [],
    }],
    'vue/max-attributes-per-line': ['error', {
      singleline: {
        max: 2,
      },
    }],
    'vue/first-attribute-linebreak': ['error', {
      singleline: 'ignore',
      multiline: 'below',
    }],
    'vue/no-multi-spaces': ['error', {
      ignoreProperties: false,
    }],
    'vue/html-closing-bracket-newline': ['error', {
      singleline: 'never',
      multiline: 'always',
    }],
  },
}
