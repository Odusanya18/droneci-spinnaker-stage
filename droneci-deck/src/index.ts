import { IDeckPlugin } from '@spinnaker/core';
import { droneCIStage } from './drone-ci/droneCIStage';

export const plugin: IDeckPlugin = {
  stages: [droneCIStage],
};
