import { IStageTypeConfig } from '@spinnaker/core';
import { DroneCIExecutionDetails } from './DroneCIExecutionDetails';
import { DroneCIStageConfig } from './DroneCIStageConfig';

export const droneCIStage: IStageTypeConfig = {
  label: 'Drone CI Build',
  description: 'Runs a Drone CI Build',
  key: 'droneCI',
  defaults: {
    failOnFailedExpressions: true,
  },
  component: DroneCIStageConfig,
  executionDetailsSections: [DroneCIExecutionDetails],
  strategy: true,
  validators: [
    {
      type: 'requiredField',
      fieldName: 'master',
      fieldLabel: 'Build Master',
    },
    {
      type: 'requiredField',
      fieldName: 'namespace',
      fieldLabel: 'Build Namespace',
    },
    {
      type: 'requiredField',
      fieldName: 'repo',
      fieldLabel: 'Repository',
    },
  ],
};
