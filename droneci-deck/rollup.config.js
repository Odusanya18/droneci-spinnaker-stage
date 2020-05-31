import typescript from 'rollup-plugin-typescript2'
import postcss from 'rollup-plugin-postcss'
import pkg from './package.json'

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
      ...Object.keys(pkg.dependencies || {}),
      ...Object.keys(pkg.peerDependencies || {}),
    ],
    plugins: [
        typescript({
          typescript: require('typescript'),
        }),
        postcss()
      ],
    }