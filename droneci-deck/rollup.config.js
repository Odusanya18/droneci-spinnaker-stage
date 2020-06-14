import typescript from 'rollup-plugin-typescript2'
import postcss from 'rollup-plugin-postcss'
import pkg from './package.json'
import externalGlobals from 'rollup-plugin-external-globals'
import nodeResolve from '@rollup/plugin-node-resolve'
import commonjs from '@rollup/plugin-commonjs'

// NOTE: Base pluginsdk rollup config does not work.

export default {
    input: 'src/index.ts',
    output: [
      { 
        dir: 'build/dist', 
        format: 'es', 
        sourcemap: true 
      },
      {
        file: pkg.main,
        format: 'cjs',
      },
      {
        file: pkg.module,
        format: 'es',
      },
    ],
    external: [
      ...Object.keys(pkg.peerDependencies || {}),
      ...Object.keys(pkg.dependencies || {}),
    ],
    plugins: [
        typescript({
          typescript: require('typescript'),
        }),
        nodeResolve(),
        commonjs(),
        externalGlobals(spinnakerSharedLibraries()),
        postcss()
      ],
    }

    function spinnakerSharedLibraries() {
      // Updates here should also be added in core/src/plugins/sharedLibraries.ts
      const libraries = [
        '@spinnaker/core',
        '@uirouter/core',
        '@uirouter/react',
        '@uirouter/rx',
        'lodash',
        'prop-types',
        'react',
        'react-dom',
        'rxjs',
        'rxjs/Observable',
      ];
    
      function getGlobalVariable(libraryName) {
        const prefix = 'spinnaker.plugins.sharedLibraries';
        const sanitizedLibraryName = libraryName.replace(/[^a-zA-Z0-9_]/g, '_');
        return `${prefix}.${sanitizedLibraryName}`;
      }
    
      return libraries.reduce((globalsMap, libraryName) => {
        return { ...globalsMap, [libraryName]: getGlobalVariable(libraryName) };
      }, {});
    }