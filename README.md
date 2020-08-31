![CI](https://github.com/odusanya18/droneci-spinnaker-stage/workflows/CI/badge.svg)
![Latest Kork](https://github.com/odusanya18/droneci-spinnaker-stage/workflows/Latest%20Kork/badge.svg?branch=master)
![Latest Orca](https://github.com/odusanya18/droneci-spinnaker-stage/workflows/Latest%20Orca/badge.svg?branch=master)

The [Drone CI Spinnaker stage](https://github.com/odusanya18/droneci-spinnaker-stage/) creates a custom pipeline stage that triggers a Drone CI Build
This plugin implements the Stage Definition builder and each stage implements an Orca task for CI operations.

The plugin consists of a `droneci-orca`, `droneci-igor`, `droneci-gate` [Kotlin](https://kotlinlang.org/docs/reference/) server components and a `drone-deck` [React](https://reactjs.org/) UI component that uses the [rollup.js](https://rollupjs.org/guide/en/#plugins-overview) plugin library.

This plugin is still **alpha** and contributions are very welcome. This plugin originates from the [proposal for Drone CI Support for Spinnaker](https://github.com/spinnaker/governance/pull/142/files?short_path=a34056b#diff-a34056b379ba57b470080ba831b9424b) and [Drone CI type for Spinnaker pipeline stage](https://summerofcode.withgoogle.com/projects/#5222740111392768).

![Drone CI configuration view](https://i.imgur.com/hQOBJ1R.png "Drone CI configuration")
# Version Compatibility
 
| Plugin  | Spinnaker Platform |
|:----------- | :--------- |
| 1.0.0  |  1.20.x |

# Usage
Configure the Spinnaker service. Put the following in the service yml to enable the plugin and configure the extension:

```
spinnaker:
    extensibility:
      plugins:
        odusanya18.drone-ci:
          enabled: true
          version: 1.0.0
          extensions:
            odusanya18.drone-ci:
              enabled: true
              config:
                masters:
                - name: master-name
                  baseUrl: http://drone-uri
                  token: private-token
                timeout: 3600
                backOffPeriod: 10
      repositories:
        droneci-spinnaker-plugin:
          url: https://raw.githubusercontent.com/Odusanya18/spinnaker-plugin-repository/master/repositories.json
```


# Debugging

To debug the `droneci`  server component inside a Spinnaker service (like Orca) using IntelliJ Idea follow these steps:

1) Run `./gradlew releaseBundle` in the plugin project.
2) Copy the generated `.plugin-ref` file under `build` in the plugin project submodule for the service to the `plugins` directory under root in the Spinnaker service that will use the plugin .
3) Link the plugin project to the service project in IntelliJ (from the service project use the `+` button in the Gradle tab and select the plugin build.gradle).
4) Configure the Spinnaker service the same way specified above.
5) Create a new IntelliJ run configuration for the service that has the VM option `-Dpf4j.mode=development` and does a `Build Project` before launch.
6) Debug away...
